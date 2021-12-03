package com.sikimu.osero.player.thinking;

import java.util.List;

import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.item.ThinkingBoard;

/**
 * 思考用ユーティリティ
 * @author sikimu
 *
 */
public class ThinkingUtil {

	/**
	 * コーナーにおける場合返す
	 * @param piece
	 * @param board
	 * @return おけない場合はnull
	 */
	public static Cell searchSetCorner(PIECE piece, ThinkingBoard board) {
		List<Cell> list = board.getCorner();
		for(Cell cell : board.getReverse(piece)) {
			if(list.contains(cell)) {
				return cell;
			}
		}
		return null;
	}
	
}
