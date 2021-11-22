package com.sikimu.osero;

import java.util.Scanner;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.mode.Title;

/**
 * �S�̂̃R���g���[��
 * @author sikimu
 *
 */
public class Controller {
	
	private static Scanner scanner;
	
	/**
	 * ���s
	 */
	public static void run() {
		
		scanner = new Scanner(System.in);
		
		Mode mode = new Title();
		do {
			mode.draw();
			mode = mode.update();
		}
		while(mode != null);
		
		scanner.close();
	}
	
	/**
	 * ��������
	 * nextInt�łƂ��Ă��܂��Ɨ�O���畜�A����ςȂ̂ł��̌`��
	 * @return�@���͂��ꂽ����
	 */
	public static int inputInt() throws Exception{

		String num = scanner.next();
		return Integer.parseInt(num);
	}
}
