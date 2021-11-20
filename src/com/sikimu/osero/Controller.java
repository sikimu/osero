package com.sikimu.osero;

import java.util.Scanner;

import com.sikimu.osero.mode.Mode;
import com.sikimu.osero.mode.Title;

/**
 * �S�̂̃R���g���[��
 * @author sikimu
 *
 */
public class Controller {
	
	/**
	 * ���s
	 */
	public static void run() {
		
		Mode mode = new Title();
		do {
			mode.draw();
			mode = mode.update();
		}
		while(mode != null);
	}
	
	/**
	 * ��������
	 * @return�@���͂��ꂽ����
	 */
	public static int inputInt() {
		
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		scanner.close();
		return num;
	}
}
