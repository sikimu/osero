package com.sikimu.osero.player.thinking;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.item.piece.Piece.COLOR;

/**
 * ���[�U�[����ōs��
 * @author sikimu
 *
 */
public class Playing extends Thinking {

	public Playing(COLOR color) {
		super(color);
	}

	@Override
	public BoardPos think(Board board) {
		
		BoardPos pos = null;
		
		do {
			pos = input(board);
		}while(pos == null);
		
		return pos;
	}

	/**
	 * ���͂������Ή����͏���
	 * @return �z�u�ӏ�
	 */
	private BoardPos input(Board board) {
		
		try {
			int no = Controller.inputInt();
			
			//10�̈ʂ���,1�̈ʂ��c
			BoardPos pos = new BoardPos((no / 10) - 1, (no % 10) - 1);
			
			if(board.isSetPiece(pos) == false) {
				Drawer.draw("�����܂���");
				return null;
			}
			if(board.countReverse(getColor(), pos) == 0) {
				Drawer.draw("�߂���܂���");
				return null;
			}

			return pos;
		} catch (Exception e) {
			Drawer.draw("���͂��s���ł��B���c�̏��Ő��l����͂��Ă��������B��F�E��̊p��91");
			return null;
		}
	}
}
