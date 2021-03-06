package com.sikimu.osero.abst;

import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;

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
	 * 駒の配置する場所の決定
	 * @param board
	 */
	public Cell getCell(Board board) {
		Cell cell = think(board);
		return cell;
	}
	
	/**
	 * 思考
	 * @param player 思考を使用する色
	 * @param board 現在のボード
	 * @return 配置箇所
	 */
	protected abstract Cell think(Board board);

	public PIECE getPiece() {
		return piece;
	}
	
}
