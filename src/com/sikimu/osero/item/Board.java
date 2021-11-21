package com.sikimu.osero.item;

import com.sikimu.osero.Drawer;
import com.sikimu.osero.item.piece.Piece;
import com.sikimu.osero.item.piece.color.Color;
import com.sikimu.osero.item.piece.color.White;

/**
 * オセロのボード
 * @author sikimu
 *
 */
public class Board {
	
	/** ボードの横幅 */
	private final static int SIZE_X = 8;
	
	/** ボードの縦幅 */
	private final static int SIZE_Y = 8;	
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 描画
	 */
	public void draw() {
		Piece piece = new Piece(new White());
		Drawer.draw(piece.toString());
	}
}
