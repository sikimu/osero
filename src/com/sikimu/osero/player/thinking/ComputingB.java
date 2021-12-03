package com.sikimu.osero.player.thinking;

import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;

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
	public Cell think(Board board) {
		
		Cell cell = board.getReverse(getPiece()).get(0);
		
		return cell;
	}
}
