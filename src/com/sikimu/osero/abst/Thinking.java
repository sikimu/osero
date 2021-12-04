package com.sikimu.osero.abst;

import java.util.ArrayList;
import java.util.List;

import com.sikimu.osero.item.Board;
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
	
	/** 配置情報　*/
	private List<Cell> setLog = new ArrayList<Board.Cell>();
	
	public Thinking(PIECE piece) {
		this.piece = piece;
	}
	
	/**
	 * 駒の配置する場所の決定
	 * @param board
	 */
	public Cell getCell(ThinkingBoard board) {
		Cell cell = think(board);
		setLog.add(cell);
		return cell;
	}
	
	/**
	 * 思考
	 * @param player 思考を使用する色
	 * @param board 現在のボード
	 * @return 配置箇所
	 */
	protected abstract Cell think(ThinkingBoard board);

	public PIECE getPiece() {
		return piece;
	}

	/**
	 * ログの結果を文字列で返す
	 * @return
	 */
	public String getLog() {
		String str = "";
		for (Cell cell : setLog) {
			str = str + cell.toString();
		}
		return str;
	}
	
	
}
