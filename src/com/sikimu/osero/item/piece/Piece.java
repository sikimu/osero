package com.sikimu.osero.item.piece;

/**
 * オセロの駒
 * @author sikimu
 *
 */
public class Piece {
	
	/**
	 * 色情報
	 * @author sikimu
	 *
	 */
	public enum COLOR{
		WHITE("W"),
		BLACK("B");
		
		/** 表記用文字列 */
		public final String colorString;
		
		/**
		 * コンストラクタ
		 * @param str 表記用文字
		 */
		COLOR(String str) {
			colorString = str;
		}		
	}
	
	/** 表面の色 */
	public COLOR color;
	
	/**
	 * コンストラクタ
	 * @param color　表面の色
	 */
	public Piece(COLOR color) {
		this.color = color;
	}
	
	public String toString() {
		return color.toString();
	}
}
