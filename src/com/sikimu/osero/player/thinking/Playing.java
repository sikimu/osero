package com.sikimu.osero.player.thinking;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.player.Player;

/**
 * ���[�U�[����ōs��
 * @author sikimu
 *
 */
public class Playing extends Thinking {

	@Override
	public BoardPos think(Player player, Board board) {
		
		BoardPos pos = null;
		
		do {
			pos = input(player, board);
		}while(pos == null);
		
		return pos;
	}

	/**
	 * ���͂������Ή����͏���
	 * @return �z�u�ӏ�
	 */
	private BoardPos input(Player player, Board board) {
		
		try {
			int no = Controller.inputInt();
			
			//10�̈ʂ���,1�̈ʂ��c
			BoardPos pos = new BoardPos(no / 10 - 1, no % 10 - 1);
			
			if(board.isSetPiece(player, pos) == false) {
				Drawer.draw("�����ɂ͒u���܂���");
				return null;
			}

			return pos;
		} catch (Exception e) {
			Drawer.draw("���͂��s���ł��B���c�̏��Ő��l����͂��Ă��������B��F�E��̊p��91");
			return null;
		}
	}
}
