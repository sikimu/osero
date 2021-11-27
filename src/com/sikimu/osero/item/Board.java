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
	 * �e�����̈ړ���
	 */
	private final static int DIRECTION[][] = {
		{0, -1},				
		{0,  1},	
		{1, -1},				
		{1,  0},	
		{1,  1},	
		{1, -1},				
		{1,  1},
	};
	
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
		return inBoard(pos.x, pos.y) && pieceList[pos.y][pos.x] == null;
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
				
		reverse(player, pos);
	}
	
	/**
	 *�@�߂���閇��(�S����)
	 * @param player //�Ώۂ̃v���C���[
	 * @param pos �Ώۂ̈ʒu
	 * @return
	 */
	public int countReverse(Player player, BoardPos pos) {
		int cnt = 0;
		
		for(int[] direction : DIRECTION) {
			cnt += countReverse(player, pos, direction[0], direction[�P]);
		}
		return cnt;
	}
	
	/**
	 * �߂���閇��
	 * @param player�@�Ώۂ̃v���C���[
	 * @param pos �Ώۂ̈ʒu
	 * @param moveX �߂����Ă�������x
	 * @param moveY �߂����Ă�������y
	 * @return �߂���閇��
	 */
	public int countReverse(Player player, BoardPos pos, int moveX, int moveY) {
		int cnt = 0;
		int x = pos.x + moveX;
		int y = pos.y + moveY;
		while(inBoard(x, y)) {
			if(pieceList[y][x] == null) {
				return 0;
			}
			if(pieceList[y][x].color == player.getColor()) {
				return cnt;
			}
			cnt++;
			x = x + moveX;
			y = y + moveY;
		}		
		return 0;
	}

	/**
	 * �S�������߂���
	 * @param player �Ώۂ̃v���C���[
	 * @param pos�@�Ώۂ̈ʒu
	 */
	public void reverse(Player player, BoardPos pos) {

		for(int[] direction : DIRECTION) {
			int cnt = countReverse(player, pos, direction[0], direction[�P]);
			if(cnt > 0) {
				reverse(player, pos, direction[0], direction[1]);
			}
		}
	}	
	
	/**
	 * �߂���
	 * @param player�@�Ώۂ̃v���C���[
	 * @param pos �Ώۂ̈ʒu
	 * @param moveX �߂����Ă�������x
	 * @param moveY �߂����Ă�������y
	 */
	private void reverse(Player player, BoardPos pos, int moveX, int moveY) {	
		int x = pos.x + moveX;
		int y = pos.y + moveY;
		while(inBoard(x, y)) {
			if(pieceList[y][x].color == player.getColor()) {
				return;
			}
			pieceList[y][x].color = player.getColor();
			x = x + moveX;
			y = y + moveY;
		}				
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
