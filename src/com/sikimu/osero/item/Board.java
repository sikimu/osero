package com.sikimu.osero.item;

import java.util.ArrayList;
import java.util.List;

import com.sikimu.osero.Drawer;
import com.sikimu.osero.item.piece.Piece;
import com.sikimu.osero.item.piece.Piece.COLOR;

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
		{-1, -1},		
		{-1,  0},
		{-1,  1},
	};
	
	/**
	 * �z�u���[y][x]
	 */
	private Piece pieceArray[][] = new Piece[8][8];
	
	/** �z�u������ */
	private List<Piece> pieceList = new ArrayList<Piece>();
	
	/**
	 * �R���X�g���N�^
	 */
	public Board() {
		//��̏����z�u
		setPiece(COLOR.BLACK, new BoardPos(3,3));
		setPiece(COLOR.BLACK, new BoardPos(4,4));
		setPiece(COLOR.WHITE, new BoardPos(3,4));
		setPiece(COLOR.WHITE, new BoardPos(4,3));
	}
	
	/**
	 * �{�[�h�ɃZ�b�g�ł��邩
	 * @return
	 */
	public boolean isSetPiece(BoardPos pos) {
		return inBoard(pos.x, pos.y) && pieceArray[pos.y][pos.x] == null;
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
		return pieceArray[0].length;
	}

	/**
	 * �{�[�h�̉���
	 * @return
	 */
	public int getHeight() {
		return pieceArray.length;
	}		
	
	/**
	 * ��̔z�u
	 * @param player �z�u����F
	 * @param pos �z�u���W
	 */
	public void setPiece(COLOR color, BoardPos pos) {		
		Piece piece = new Piece(color);
		pieceArray[pos.y][pos.x] = piece;
		pieceList.add(piece);
				
		reverse(color, pos);
	}
	
	/**
	 * �u����ꏊ�̎擾
	 * @param player�@�Ώۂ̐F
	 * @return �u����ꏊ�̃��X�g
	 */
	public List<BoardPos> getReverse(COLOR color){
		ArrayList<BoardPos> list = new ArrayList<BoardPos>();
		
		for(int y = 0;y < pieceArray.length;y++) {
			for(int x = 0;x < pieceArray[y].length;x++) {
				if(pieceArray[y][x] != null) {
					continue;
				}
				BoardPos pos = new BoardPos(x, y);
				if(countReverse(color, pos) > 0) {
					list.add(pos);
				}
			}
		}	
		
		return list;
	}
	
	/**
	 *�@�߂���閇��(�S����)
	 * @param player �Ώۂ̐F
	 * @param pos �Ώۂ̈ʒu
	 * @return
	 */
	public int countReverse(COLOR color, BoardPos pos) {
		int cnt = 0;
		
		for(int[] direction : DIRECTION) {
			cnt += countReverse(color, pos, direction[0], direction[�P]);
		}
		return cnt;
	}
	
	/**
	 * �߂���閇��
	 * @param player�@�Ώۂ̐F
	 * @param pos �Ώۂ̈ʒu
	 * @param moveX �߂����Ă�������x
	 * @param moveY �߂����Ă�������y
	 * @return �߂���閇��
	 */
	public int countReverse(COLOR color, BoardPos pos, int moveX, int moveY) {
		int cnt = 0;
		int x = pos.x + moveX;
		int y = pos.y + moveY;
		while(inBoard(x, y)) {
			if(pieceArray[y][x] == null) {
				return 0;
			}
			if(pieceArray[y][x].color == color) {
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
	 * @param color �Ώۂ̐F
	 * @param pos�@�Ώۂ̈ʒu
	 */
	public void reverse(COLOR color, BoardPos pos) {

		for(int[] direction : DIRECTION) {
			int cnt = countReverse(color, pos, direction[0], direction[�P]);
			if(cnt > 0) {
				reverse(color, pos, direction[0], direction[1]);
			}
		}
	}	
	
	/**
	 * �߂���
	 * @param player�@�Ώۂ̐F
	 * @param pos �Ώۂ̈ʒu
	 * @param moveX �߂����Ă�������x
	 * @param moveY �߂����Ă�������y
	 */
	private void reverse(COLOR color, BoardPos pos, int moveX, int moveY) {	
		int x = pos.x + moveX;
		int y = pos.y + moveY;
		while(inBoard(x, y)) {
			if(pieceArray[y][x].color == color) {
				return;
			}
			pieceArray[y][x].color = color;
			x = x + moveX;
			y = y + moveY;
		}				
	}
	
	/**
	 * �`��
	 */
	public void draw() {
		Drawer.draw(" 12345678");//�c�̗�̔ԍ�
		for(int i = 0;i < pieceArray.length;i++) {
			String str = "" + (i + 1);//���̗�̔ԍ�
			for(Piece piece : pieceArray[i]) {
				if(piece == null) {
					str = str + "+";
				}
				else {
					str = str + piece.color.colorString;
				}
			}
			Drawer.draw(str);//1�s�Â�
		}		
	}

	/**
	 * ����
	 * @return�@���ʂ̕�����
	 */
	public String createResult() {
		long b = pieceList.stream().filter(piece -> piece.color == COLOR.BLACK).count();
		long w = pieceList.stream().filter(piece -> piece.color == COLOR.WHITE).count();
		
		return "���� B:" + b + " W:" + w;
	}
}
