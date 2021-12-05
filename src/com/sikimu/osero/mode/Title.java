package com.sikimu.osero.mode;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.player.thinking.ComputingB;
import com.sikimu.osero.player.thinking.ComputingW;
import com.sikimu.osero.player.thinking.Playing;

/**
 * �^�C�g��
 * �Q�[�����[�h�̑I��������Ƃ���
 * @author sikimu
 *
 */
public class Title extends Mode {

	@Override
	public void draw() {
		Drawer.draw("�R���\�[���I�Z��");
		Drawer.draw("���:1 ���:2 CPU�̂�:3 �����O:4 �����O:5");
	}

	@Override
	public Mode update() {
		Thinking first = null;
		Thinking second = null;
		int no = -1;
		do {
			no  = input();
			switch(no) {
			case 1:
				first = new Playing(PIECE.BLACK);
				second = new ComputingW(PIECE.WHITE);
				break;
			case 2:
				first = new ComputingB(PIECE.BLACK);
				second = new Playing(PIECE.WHITE);
				break;
			case 3:
				first = new ComputingB(PIECE.BLACK);
				second = new ComputingW(PIECE.WHITE);
				break;
			case 4:
				LosingConfirmedInfo.printLog(PIECE.BLACK);
				no = -1;
				break;
			case 5:
				LosingConfirmedInfo.printLog(PIECE.WHITE);
				no = -1;
				break;	
			default:
				no = -1;
				break;
			}
		}while(no == -1);
		return new Game(first, second);
	}
	
	/**
	 * ���͎�t
	 * @return
	 */
	private int input() {
		try {
			return Controller.inputInt();
		} catch (Exception e) {
			return -1;
		}
	}
}
