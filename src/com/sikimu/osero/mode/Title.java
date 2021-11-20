package com.sikimu.osero.mode;

import java.util.Scanner;

/**
 * �^�C�g��
 * �Q�[�����[�h�̑I��������Ƃ���
 * @author sikimu
 *
 */
public class Title extends Mode {

	@Override
	public void draw() {
		System.out.println("���:1 ���:2");
	}

	@Override
	public Mode update() {
		Scanner scanner = new Scanner(System.in);
		int i = scanner.nextInt();
		return new Game();
	}
}
