package com.sikimu.osero.item;

import com.sikimu.osero.Drawer;
import com.sikimu.osero.item.piece.Piece;
import com.sikimu.osero.item.piece.color.Color;
import com.sikimu.osero.item.piece.color.White;

/**
 * �I�Z���̃{�[�h
 * @author sikimu
 *
 */
public class Board {
	
	/** �{�[�h�̉��� */
	private final static int SIZE_X = 8;
	
	/** �{�[�h�̏c�� */
	private final static int SIZE_Y = 8;	
	
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * �`��
	 */
	public void draw() {
		Piece piece = new Piece(new White());
		Drawer.draw(piece.toString());
	}
}
