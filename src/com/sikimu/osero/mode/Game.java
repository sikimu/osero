
package com.sikimu.osero.mode;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.Cell;
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
	private Thinking firstThinking;
	
	/** 後手 */
	private Thinking secondThinking;
	
	/**
	 * コンストラクタ
	 * @param first 先手
	 * @param second　後手
	 */
	public Game(Thinking first, Thinking second) {
		
		firstThinking = first;
		secondThinking = second;
		
		//ボードにプレイヤーをsetする
		board = new ThinkingBoard();
		
		move = firstThinking;
	}
	
	@Override
	public void draw() {
		board.draw();
	}

	@Override
	public Mode update() {
		
		Thinking next;// 次の思考
		
		Cell cell = move.think(board);
		board.setPiece(move.getPiece(), cell);
		next = move == firstThinking ? secondThinking : firstThinking;

		if(board.getReverse(next.getPiece()).size() == 0) {//相手がめくれない
			if(board.getReverse(move.getPiece()).size() == 0) {// どちらもめくれないので終了
				return new Result(board);
			}	
			return this;
		}		
		move = next;
		return this;
	}

}
