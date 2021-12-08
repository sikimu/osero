package com.sikimu.osero.player.thinking;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;

/**
 * ���[�U�[����ōs��
 * @author sikimu
 *
 */
public class Playing extends Thinking {

	public Playing(PIECE piece) {
		super(piece);
	}

	@Override
	public Cell think(Board board) {
		
		Cell cell = null;
		
		do {
			cell = input(board);
		}while(cell == null);
		
		return cell;
	}

	/**
	 * ���͂������Ή����͏���
	 * @return �z�u�ӏ�
	 */
	private Cell input(Board board) {
		
		try {
			int no = Controller.inputInt();
			
			//10�̈ʂ���,1�̈ʂ��c
			Cell cell = board.getCell((no / 10) - 1, (no % 10) - 1);
			
			if(cell == null) {
				Drawer.draw("�����܂���");
				return null;
			}
			if(board.countReverse(getPiece(), cell) == 0) {
				Drawer.draw("�߂���܂���");
				return null;
			}

			return cell;
		} catch (Exception e) {
			Drawer.draw("���͂��s���ł��B���c�̏��Ő��l����͂��Ă��������B��F�E��̊p��91");
			return null;
		}
	}
}
