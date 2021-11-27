package com.sikimu.osero.player.thinking;

import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.player.Player;

/**
 * ���[�U�[����ōs��
 * @author sikimu
 *
 */
public class Computing extends Thinking {

	@Override
	public BoardPos think(Player player, Board board) {
		
		BoardPos pos = board.getReverse(player).get(0);
		
		return pos;
	}
}
