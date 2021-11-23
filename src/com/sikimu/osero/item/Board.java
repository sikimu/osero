package com.sikimu.osero.item;

import java.util.ArrayList;
import java.util.List;

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
	 * �{�[�h�ɃZ�b�g�ł��邩
	 * @return
	 */
	public boolean isSetPiece(Player player, BoardPos pos) {
		return inBoard(pos.x, pos.y);
	}
	
	/**
	 * �w����W���{�[�h��̍��W���H
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean inBoard(int x, int y) {
		if(x <  0 || getWidth() <= x) {
			return false;
		}
		if(y <  0 || getHeight() <= y) {
			return false;
		}
		return true;
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
				
		BoardPos p = search(player, pos.x, pos.y , 1, 0);
	}
	
	private BoardPos search(Player player, int x, int y, int moveX, int moveY) {				
		x = x + moveX;
		y = y + moveY;
		if(inBoard(x, y) == false || pieceList[y][x] == null) {
			return null;
		}
		if(pieceList[y][x].color == player.getColor()) {
			return new BoardPos(x, y);
		}
		return search(player, x, y, moveX, moveY);
	}
	
	public void reverse(List<Piece> list, Player player, int moveX, int moveY) {
		
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
