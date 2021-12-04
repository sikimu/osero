package com.sikimu.osero.player.thinking;

import java.util.ArrayList;
import java.util.List;

import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.item.ThinkingBoard;

/**
 * �v�l�p���[�e�B���e�B
 * @author sikimu
 *
 */
public class ThinkingUtil {

	/**
	 * �R�[�i�[�ɂ�����ꍇ�Ԃ�
	 * @param piece
	 * @param board
	 * @return �����Ȃ��ꍇ��null
	 */
	public static List<Cell> searchSetCorner(PIECE piece, ThinkingBoard board) {

		List<Cell> list = new ArrayList<Cell>();
		
		List<Cell> cornerlist = board.getCorner();		
		for(Cell cell : board.getReverse(piece)) {
			if(cornerlist.contains(cell)) {
				list.add(cell);
			}
		}
		return list;
	}
	
}
