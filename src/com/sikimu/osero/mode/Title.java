package com.sikimu.osero.mode;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Mode;

/**
 * �^�C�g��
 * �Q�[�����[�h�̑I��������Ƃ���
 * @author sikimu
 *
 */
public class Title extends Mode {

	@Override
	public void draw() {
		Drawer.draw("���:1 ���:2");
	}

	@Override
	public Mode update() {
		int no = -1;
		do {
			no  = input();
		}while(no == -1);
		return new Game();
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
