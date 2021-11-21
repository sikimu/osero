package com.sikimu.osero.player;

import com.sikimu.osero.player.thinking.Thinking;

/**
 * �I�Z�������l
 * �l�Ԃ�������CPU��������
 * @author sikimu
 *
 */
public class Player {

	/** �g�p���Ă���v�l */
	private Thinking thinking;
	
	/**
	 * �R���X�g���N�^
	 * @param thinking�@�g�p����v�l
	 */
	public Player(Thinking thinking) {
		this.thinking = thinking;
	}
	
	
	/**
	 * �v�l���ʂ�Ԃ�
	 * @return
	 */
	public int think() {
		return thinking.think();
	}
}
