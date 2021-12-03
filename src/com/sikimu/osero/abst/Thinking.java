package com.sikimu.osero.abst;

import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.item.ThinkingBoard;

/**
 * 思考の基底クラス
 * 何を基準に結果を決めるか
 * @author sikimu
 *
 */
public abstract class Thinking {
	
	/** 自分の駒　*/
	private PIECE piece;
	
	public Thinking(PIECE piece) {
		this.piece = piece;
	}
	
	/**
	 * 思考
	 * @param player 思考を使用する色
	 * @param board 現在のボード
	 * @return 配置箇所
	 */
	public abstract Cell think(ThinkingBoard board);

	public PIECE getPiece() {
		return piece;
	}
	
	
}
