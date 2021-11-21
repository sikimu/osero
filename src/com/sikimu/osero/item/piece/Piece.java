package com.sikimu.osero.item.piece;

import com.sikimu.osero.item.piece.color.Color;

/**
 * オセロの駒
 * @author sikimu
 *
 */
public class Piece {
	
	/** 表面の色 */
	public Color surfaceColor;
	
	/**
	 * コンストラクタ
	 * @param color　表面の色
	 */
	public Piece(Color color) {
		surfaceColor = color;
	}
	
	public String toString() {
		return surfaceColor.toString();
	}
}
