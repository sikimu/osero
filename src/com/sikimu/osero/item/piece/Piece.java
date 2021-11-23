package com.sikimu.osero.item.piece;

import com.sikimu.osero.abst.Color;

/**
 * オセロの駒
 * @author sikimu
 *
 */
public class Piece {
	
	/** 表面の色 */
	public Color color;
	
	/**
	 * コンストラクタ
	 * @param color　表面の色
	 */
	public Piece(Color color) {
		this.color = color;
	}
	
	public String toString() {
		return color.toString();
	}
}
