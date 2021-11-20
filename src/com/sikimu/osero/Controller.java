package com.sikimu.osero;

import java.util.Scanner;

import com.sikimu.osero.mode.Mode;
import com.sikimu.osero.mode.Title;

/**
 * 全体のコントロール
 * @author sikimu
 *
 */
public class Controller {
	
	/**
	 * 実行
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
	 * 数字入力
	 * @return　入力された結果
	 */
	public static int inputInt() {
		
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		scanner.close();
		return num;
	}
}
