package com.sikimu.osero;

import com.sikimu.osero.mode.Mode;
import com.sikimu.osero.mode.Title;

public class ModeController {
	
	/**
	 * é¿çs
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
