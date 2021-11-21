package com.sikimu.osero.player.thinking;

import com.sikimu.osero.Controller;

/**
 * ユーザー操作で行う
 * @author sikimu
 *
 */
public class Playing extends Thinking {

	@Override
	public int think() {
		
		return Controller.inputInt();
	}

}
