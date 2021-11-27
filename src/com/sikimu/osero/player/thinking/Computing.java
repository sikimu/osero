package com.sikimu.osero.player.thinking;

import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.item.piece.Piece.COLOR;

/**
 * ユーザー操作で行う
 * @author sikimu
 *
 */
public class Computing extends Thinking {

	@Override
	public BoardPos think(COLOR color, Board board) {
		
		BoardPos pos = board.getReverse(color).get(0);
		
		return pos;
	}
}
