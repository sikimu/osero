package com.sikimu.osero.player.thinking;

import com.sikimu.osero.Controller;
import com.sikimu.osero.abst.Thinking;

/**
 * ���[�U�[����ōs��
 * @author sikimu
 *
 */
public class Playing extends Thinking {

	@Override
	public int think() {
		
		return Controller.inputInt();
	}

}
