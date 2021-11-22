package com.sikimu.osero;

import java.util.Scanner;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.mode.Title;

/**
 * 全体のコントロール
 * @author sikimu
 *
 */
public class Controller {
	
	private static Scanner scanner;
	
	/**
	 * 実行
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
	 * 数字入力
	 * nextIntでとってしまうと例外から復帰が大変なのでこの形式
	 * @return　入力された結果
	 */
	public static int inputInt() throws Exception{

		String num = scanner.next();
		return Integer.parseInt(num);
	}
}
