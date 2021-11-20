package com.sikimu.osero;

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
}
