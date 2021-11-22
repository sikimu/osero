package com.sikimu.osero.player;

import com.sikimu.osero.abst.Color;
import com.sikimu.osero.abst.Thinking;

/**
 * �I�Z�������l
 * �l�Ԃ�������CPU��������
 * @author sikimu
 *
 */
public class Player {

	/** �F */
	private Color color;
	
	/** �g�p���Ă���v�l */
	private Thinking thinking;
	
	/**
	 * �R���X�g���N�^
	 * @param color �g�p����F
	 * @param thinking�@�g�p����v�l
	 */
	public Player(Color color, Thinking thinking) {
		this.color = color;
		this.thinking = thinking;
	}
	
	
	/**
	 * �v�l���ʂ�Ԃ�
	 * @return �z�u����ԍ�
	 */
	public int think() {
		return thinking.think();
	}


	/**
	 * �v���C���[�̐F�̎擾
	 * @return �v���C���[�̐F
	 */
	public Color getColor() {
		return color;
	}
}
