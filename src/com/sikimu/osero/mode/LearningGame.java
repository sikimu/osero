package com.sikimu.osero.mode;

import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.player.thinking.Computing;

/**
 * ゲーム中(Learning専用)
 * もう少しクラスの作り方を検討したい
 * @author sikimu
 *
 */
public class LearningGame extends Mode {
	
	/** 手番 **/
	private Thinking move;
	
	/** ゲームで使用するボード */
	private Board board;
	
	/**　再挑戦用 */
	private Board reBoard;
	
	/**　再挑戦用 */
	private Thinking reMove;
	
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
	 * @param m 手番
	 */
	public LearningGame(Board board, Thinking thinkB, Thinking thinkW, Thinking m, int repeat) {
		
		this.repeat = repeat;
		
		brackThinking = thinkB;
		whiteThinking = thinkW;

		//ボードにプレイヤーをsetする
		this.board = board;
		reBoard = Board.createCopy(board);
		
		move = m;
	}
	
	@Override
	public void draw() {
		//board.draw();
	}

	@Override
	public Mode update() {
		
		if(board.getCount(PIECE.NONE) == 10) {
			reBoard = Board.createCopy(board);
			reMove = move;
		}
		
		Thinking next;// 次の思考
		
		Cell cell = move.getCell(board);
		if(cell == null) {
			//連続再戦(CPUのみ)
			if(repeat-- > 0) {
				if(repeat % 10000 == 0) {
					System.out.println("repeat=" + repeat);
				}
				return new LearningGame(new Board(), brackThinking, whiteThinking, brackThinking, repeat);
			}
			return new Result(board);
		}
		board.setPiece(move.getPiece(), cell);
		next = move == brackThinking ? whiteThinking : brackThinking;

		if(board.getReverse(next.getPiece()).size() == 0) {//相手がめくれない
			if(board.getReverse(move.getPiece()).size() == 0) {// どちらもめくれないので終了
				setLosing();
				//連続再戦(CPUのみ)
				if(repeat-- > 0) {
					if(repeat % 10000 == 0) {
						System.out.println("repeat=" + repeat);
					}
					return new LearningGame(reBoard, brackThinking, whiteThinking, reMove, repeat);
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
				LosingConfirmedInfo.add(PIECE.WHITE, board.getLog());
				break;
			case WHITE:
				LosingConfirmedInfo.add(PIECE.BLACK, board.getLog());
				break;
			case NONE:
				LosingConfirmedInfo.add(PIECE.BLACK, board.getLog());
				LosingConfirmedInfo.add(PIECE.WHITE, board.getLog());
				break;
		}
	}
}
