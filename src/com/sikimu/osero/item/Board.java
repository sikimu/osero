package com.sikimu.osero.item;

import java.util.ArrayList;

import com.sikimu.osero.Drawer;
import com.sikimu.osero.item.piece.Piece;
import com.sikimu.osero.item.piece.color.White;
import com.sikimu.osero.player.Player;

/**
 * �I�Z���̃{�[�h
 * @author sikimu
 *
 */
public class Board {
	
	/**
	 * �z�u���[y][x]
	 */
	private Piece pieceList[][] = new Piece[8][8];
	
	/**
	 * �R���X�g���N�^
	 * @param playerList
	 */
	public Board(ArrayList<Player> playerList) {
		//��̏����z�u
		setPiece(playerList.get(0), 3, 3);
		setPiece(playerList.get(0), 4, 4);
		setPiece(playerList.get(1), 3, 4);
		setPiece(playerList.get(1), 4, 3);
	}
	
	/**
	 * ��̔z�u
	 * @param player �z�u����v���C���[
	 * @param x �z�ux���W
	 * @param y �z�uy���W
	 */
	private void setPiece(Player player, int x, int y) {
		
		Piece piece = new Piece(player.getColor());
		pieceList[y][x] = piece;
	}
	
	/**
	 * �`��
	 */
	public void draw() {
		for(Piece[] list: pieceList) {
			String str = "";
			for(Piece piece : list) {
				if(piece == null) {
					str = str + "00";
				}
				else {
					str = str + piece.toString();
				}
			}
			Drawer.draw(str);//1�s�Â�
		}		
	}
}
