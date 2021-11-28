package com.sikimu.osero.player.thinking;

import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.item.piece.Piece.COLOR;

/**
 * CPU
 * @author sikimu
 *
 */
public class Computing extends Thinking {

	public Computing(COLOR color) {
		super(color);
	}

	@Override
	public BoardPos think(Board board) {
		
		BoardPos pos = board.getReverse(getColor()).get(0);
		
		return pos;
	}
}
