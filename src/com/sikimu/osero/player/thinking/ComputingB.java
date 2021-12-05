package com.sikimu.osero.player.thinking;

import java.util.ArrayList;
import java.util.List;

import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.item.ThinkingBoard;

/**
 * CPUçïóp
 * @author sikimu
 *
 */
public class ComputingB extends Thinking {

	public ComputingB(PIECE piece) {
		super(piece);
	}

	@Override
	public Cell think(ThinkingBoard board) {		

		ArrayList<Cell> list = new ArrayList<Cell>();

		list.addAll(board.getReverse(getPiece()));
		
		LosingConfirmedInfo.deleteConfirmed((Thinking)this, list);
		if(list.size() == 0) {
			return board.getReverse(getPiece()).get(0);
		}
		
		List<Cell> corners = ThinkingUtil.searchSetCorner(getPiece(), board);
		for (Cell cell : list) {
			if(corners.contains(cell)){
				return cell;
			}
		}
		return list.get(0);
	}
}
