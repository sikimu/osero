package com.sikimu.osero.mode;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;

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
		Controller.inputInt();
		return new Game();
	}
}
