package com.sikimu.osero.player.thinking;

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

		Cell cell = null;
		
		cell = ThinkingUtil.searchSetCorner(getPiece(), board);
		if(cell != null) {
			return cell;
		}
		
		cell = board.getReverse(getPiece()).get(0);		
		return cell;
	}
}
