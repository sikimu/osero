package com.sikimu.osero.player.thinking;

import java.util.ArrayList;

import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;

/**
 * CPUçïóp
 * @author sikimu
 *
 */
public class Computing extends Thinking {

	public Computing(PIECE piece) {
		super(piece);
	}

	@Override
	public Cell think(Board board) {		

		ArrayList<Cell> list = new ArrayList<Cell>();

		list.addAll(board.getReverse(getPiece()));
		
		LosingConfirmedInfo.deleteConfirmed(getPiece(), board.getLog(), list);
		if(list.size() == 0) {
			return null;
		}
		
		return list.get(0);
	}
}
