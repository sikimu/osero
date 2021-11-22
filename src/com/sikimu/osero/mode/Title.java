package com.sikimu.osero.mode;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Mode;

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
		try {
			Controller.inputInt();
		} catch (Exception e) {
			return update();
		}
		return new Game();
	}
}
