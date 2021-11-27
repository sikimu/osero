package com.sikimu.osero.item.piece;

/**
 * �I�Z���̋�
 * @author sikimu
 *
 */
public class Piece {
	
	/**
	 * �F���
	 * @author sikimu
	 *
	 */
	public enum COLOR{
		WHITE("W"),
		BLACK("B");
		
		/** �\�L�p������ */
		public final String colorString;
		
		/**
		 * �R���X�g���N�^
		 * @param str �\�L�p����
		 */
		COLOR(String str) {
			colorString = str;
		}		
	}
	
	/** �\�ʂ̐F */
	public COLOR color;
	
	/**
	 * �R���X�g���N�^
	 * @param color�@�\�ʂ̐F
	 */
	public Piece(COLOR color) {
		this.color = color;
	}
	
	public String toString() {
		return color.toString();
	}
}
