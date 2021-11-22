package com.sikimu.osero.item;

import java.util.ArrayList;

import com.sikimu.osero.Drawer;
import com.sikimu.osero.item.piece.Piece;
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
		setPiece(playerList.get(0), new BoardPos(3,3));
		setPiece(playerList.get(0), new BoardPos(4,4));
		setPiece(playerList.get(1), new BoardPos(3,4));
		setPiece(playerList.get(1), new BoardPos(4,3));
	}
	
	/**
	 * �{�[�h�̉���
	 * @return
	 */
	public int getWidth() {
		return pieceList[0].length;
	}

	/**
	 * �{�[�h�̉���
	 * @return
	 */
	public int getHeight() {
		return pieceList.length;
	}	
	
	/**
	 * ��̔z�u
	 * @param player �z�u����v���C���[
	 * @param pos �z�u���W
	 */
	public void setPiece(Player player, BoardPos pos) {
		
		Piece piece = new Piece(player.getColor());
		pieceList[pos.y][pos.x] = piece;
	}
	
	/**
	 * �`��
	 */
	public void draw() {
		Drawer.draw(" 12345678");//�c�̗�̔ԍ�
		for(int i = 0;i < pieceList.length;i++) {
			String str = "" + (i + 1);//���̗�̔ԍ�
			for(Piece piece : pieceList[i]) {
				if(piece == null) {
					str = str + "+";
				}
				else {
					str = str + piece.toString();
				}
			}
			Drawer.draw(str);//1�s�Â�
		}		
	}
}
