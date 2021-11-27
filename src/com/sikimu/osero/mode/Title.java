package com.sikimu.osero.mode;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.item.piece.color.Black;
import com.sikimu.osero.item.piece.color.White;
import com.sikimu.osero.player.Player;
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
		Player p1 = null;
		Player p2 = null;
		int no = -1;
		do {
			no  = input();
			switch(no) {
			case 1:
				p1 = new Player(new White(), new Playing());
				p2 = new Player(new Black(), new Computing());
				break;
			case 2:
				p1 = new Player(new White(), new Computing());
				p2 = new Player(new Black(), new Playing());
				break;
			default:
				no = -1;
				break;
			}
		}while(no == -1);
		return new Game(p1, p2);
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
