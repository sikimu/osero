package com.sikimu.osero.mode;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.player.thinking.Computing;
import com.sikimu.osero.player.thinking.Playing;

/**
 * タイトル
 * ゲームモードの選択をするところ
 * @author sikimu
 *
 */
public class Title extends Mode {

	/** 時間計測用　*/
	private static long lastTime;
	
	@Override
	public void draw() {
		System.out.println(System.currentTimeMillis() - lastTime);
		Drawer.draw("コンソールオセロ");
		Drawer.draw("先手:1 後手:2 CPUのみ:3 ログ:4");
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
				first = new Playing(PIECE.BLACK);
				second = new Computing(PIECE.WHITE);
				break;
			case 2:
				first = new Computing(PIECE.BLACK);
				second = new Playing(PIECE.WHITE);
				break;
			case 3:
				first = new Computing(PIECE.BLACK);
				second = new Computing(PIECE.WHITE);
				break;
			case 4:
				LosingConfirmedInfo.printLog();
				no = -1;
				break;
			//裏モード
			case 0:
				lastTime = System.currentTimeMillis();
				first = new Computing(PIECE.BLACK);
				second = new Computing(PIECE.WHITE);
				return new Game(first, second, 10000);
			default:
				no = -1;
				break;
			}
		}while(no == -1);
		return new Game(first, second, 0);
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
