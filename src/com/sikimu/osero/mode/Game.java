
package com.sikimu.osero.mode;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.item.piece.Piece.COLOR;

/**
 * ゲーム中
 * @author sikimu
 *
 */
public class Game extends Mode {
	
	/** 手番 **/
	private COLOR moveColor;
	
	/** ゲームで使用するボード */
	private Board board;
	
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
		board = new Board();
		
		moveColor = COLOR.BLACK;
	}
	
	@Override
	public void draw() {
		board.draw();
	}

	@Override
	public Mode update() {
		
		COLOR next;// 次の色
		
		if(moveColor == COLOR.BLACK) {
			BoardPos pos = firstThinking.think(COLOR.BLACK, board);
			board.setPiece(COLOR.BLACK, pos);
			next = COLOR.WHITE;
		}
		else {
			BoardPos pos = secondThinking.think(COLOR.WHITE, board);
			board.setPiece(COLOR.WHITE, pos);
			next = COLOR.BLACK;
		}
		if(board.getReverse(next).size() > 0) {//めくれるので次へ
			moveColor = next;
			return this;
		}
		else if(board.getReverse(moveColor).size() == 0) {// どちらもめくれないので終了
			return new Title();
		}		
		
		return this;//再度自分の手番
	}

}
