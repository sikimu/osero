package com.sikimu.osero;

import com.sikimu.osero.mode.Mode;
import com.sikimu.osero.mode.Title;

/**
 * �S�̂̃R���g���[��
 * @author sikimu
 *
 */
public class Controller {
	
	/**
	 * ���s
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
