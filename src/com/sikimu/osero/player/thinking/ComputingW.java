package com.sikimu.osero.player.thinking;

import java.util.ArrayList;
import java.util.List;

import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.item.ThinkingBoard;

/**
 * CPU”’—p
 * @author sikimu
 *
 */
public class ComputingW extends Thinking {

	public ComputingW(PIECE piece) {
		super(piece);
	}

	@Override
	public Cell think(ThinkingBoard board) {		

		ArrayList<Cell> list = new ArrayList<Cell>();

		List<Cell> corners = ThinkingUtil.searchSetCorner(getPiece(), board);
		list.addAll(corners);

		list.addAll(board.getReverse(getPiece()));
		
		LosingConfirmedInfo.deleteConfirmed((Thinking)this, list);
		
		if(list.size() > 0) {
			return list.get(0);
		}
		else {
			return board.getReverse(getPiece()).get(0);
		}
	}
}
