package com.sikimu.osero.item.piece;

import com.sikimu.osero.abst.Color;

/**
 * �I�Z���̋�
 * @author sikimu
 *
 */
public class Piece {
	
	/** �\�ʂ̐F */
	public Color color;
	
	/**
	 * �R���X�g���N�^
	 * @param color�@�\�ʂ̐F
	 */
	public Piece(Color color) {
		this.color = color;
	}
	
	public String toString() {
		return color.toString();
	}
}
