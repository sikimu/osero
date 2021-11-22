package com.sikimu.osero.player.thinking;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;

/**
 * ���[�U�[����ōs��
 * @author sikimu
 *
 */
public class Playing extends Thinking {

	@Override
	public BoardPos think(Board board) {
		
		return input(board);
	}

	/**
	 * ���͂������Ή����͏���
	 * @return �z�u�ӏ�
	 */
	private BoardPos input(Board board) {
		
		try {
			int no = Controller.inputInt();
			
			//10�̈ʂ���
			int x = no / 10 - 1;
			int y = no % 10 - 1;
			
			if(x <  0 || board.getWidth() <= x) {
				throw new Exception();
			}
			if(y <  0 || board.getHeight() <= y) {
				throw new Exception();
			}
			
			return new BoardPos(x, y);
		} catch (Exception e) {
			Drawer.draw("���͂��s���ł��B���c�̏��Ő��l����͂��Ă��������B��F�E��̊p��91");
			return input(board);
		}
	}
}
