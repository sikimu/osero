package com.sikimu.osero.mode;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.piece.Piece.COLOR;
import com.sikimu.osero.player.thinking.Computing;
import com.sikimu.osero.player.thinking.Playing;

/**
 * タイトル
 * ゲームモードの選択をするところ
 * @author sikimu
 *
 */
public class Title extends Mode {

	@Override
	public void draw() {
		Drawer.draw("先手:1 後手:2");
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
				first = new Playing(COLOR.BLACK);
				second = new Computing(COLOR.WHITE);
				break;
			case 2:
				first = new Computing(COLOR.BLACK);
				second = new Playing(COLOR.WHITE);
				break;
			case 3:
				first = new Computing(COLOR.BLACK);
				second = new Computing(COLOR.WHITE);
				break;
			default:
				no = -1;
				break;
			}
		}while(no == -1);
		return new Game(first, second);
	}
	
	/**
	 * 入力受付
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
