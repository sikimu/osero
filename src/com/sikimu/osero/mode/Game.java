
package com.sikimu.osero.mode;

import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.player.thinking.ComputingB;
import com.sikimu.osero.player.thinking.ComputingW;
import com.sikimu.osero.item.ThinkingBoard;

/**
 * ゲーム中
 * @author sikimu
 *
 */
public class Game extends Mode {
	
	/** 手番 **/
	private Thinking move;
	
	/** ゲームで使用するボード */
	private ThinkingBoard board;
	
	/** 先手 */
	private Thinking brackThinking;
	
	/** 後手 */
	private Thinking whiteThinking;

	/** 繰り返し回数 */
	private int repeat;
	
	/**
	 * コンストラクタ
	 * @param thinkB 先手
	 * @param thinkW　後手
	 */
	public Game(Thinking thinkB, Thinking thinkW, int repeat) {
		
		this.repeat = repeat;
		
		brackThinking = thinkB;
		whiteThinking = thinkW;

		//ボードにプレイヤーをsetする
		board = new ThinkingBoard();
		
		move = brackThinking;
	}
	
	@Override
	public void draw() {
		
		//連戦中は表示しない
		if(repeat > 0) {
			System.out.println("repeat" + repeat);
			return;
		}
		
		board.draw();
	}

	@Override
	public Mode update() {
		
		Thinking next;// 次の思考
		
		Cell cell = move.getCell(board);
		board.setPiece(move.getPiece(), cell);
		next = move == brackThinking ? whiteThinking : brackThinking;

		if(board.getReverse(next.getPiece()).size() == 0) {//相手がめくれない
			if(board.getReverse(move.getPiece()).size() == 0) {// どちらもめくれないので終了
				setLosing();
				//連続再戦(CPUのみ)
				if(repeat-- > 0) {
					Thinking first = new ComputingB(PIECE.BLACK);
					Thinking second = new ComputingW(PIECE.WHITE);
					return new Game(first, second, repeat);
				}
				return new Result(board);
			}	
			return this;
		}		
		move = next;
		return this;
	}

	/**
	 * 負けを登録
	 */
	private void setLosing() {
		PIECE win = board.getWin();
		switch(win){
			case BLACK:
				LosingConfirmedInfo.add(whiteThinking);
				break;
			case WHITE:
				LosingConfirmedInfo.add(brackThinking);
				break;
			case NONE:
				LosingConfirmedInfo.add(brackThinking);
				LosingConfirmedInfo.add(whiteThinking);
				break;
		}
	}
}
