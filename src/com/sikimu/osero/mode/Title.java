package com.sikimu.osero.mode;

import java.util.Scanner;

/**
 * タイトル
 * ゲームモードの選択をするところ
 * @author sikimu
 *
 */
public class Title extends Mode {

	@Override
	public void draw() {
		System.out.println("先手:1 後手:2");
	}

	@Override
	public Mode update() {
		Scanner scanner = new Scanner(System.in);
		int i = scanner.nextInt();
		return new Game();
	}
}
