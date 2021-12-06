package com.sikimu.osero.item;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sikimu.osero.Drawer;

/**
 * �I�Z���̃{�[�h
 * @author sikimu
 *
 */
public class Board {
	
	/** ���� */
	protected final static int WIDTH = 8;
	
	/** �c�� */
	protected final static int HEIGHT = 8;
	
	/** �z�u���@*/
	private String log = "";			
	
	/**
	 * �}�X����݂�����
	 * @author sikimu
	 *
	 */
	public enum DIRECTION{
		TOP,
		LEFT,
		RIGHT,
		BOTTOM,
		TOP_L,
		TOP_R,
		BOTTOM_L,
		BOTTOM_R,
	}
	
	/**
	 * ����
	 * @author sikimu
	 *
	 */
	public enum PIECE{
		NONE("+"),		
		WHITE("W"),
		BLACK("B");
		
		/** �\�L�p������ */
		public final String colorString;
		
		/**
		 * �R���X�g���N�^
		 * @param str �\�L�p����
		 */
		PIECE(String str) {
			colorString = str;
		}
	}	
	
	/**
	 * �}�X
	 * �{�[�h�o�R�ł̂ݎ󂯎���悤�ɂ���̂�private�ō����get�����W�ȊO�Ȃ�
	 * @author sikimu
	 *
	 */
	public class Cell{
		private int x;
		private int y;
		
		/**
		 * ����̃Z��
		 */
		private Map<DIRECTION, Cell> AroundMap = new LinkedHashMap<Board.DIRECTION, Board.Cell>();
		
		/** �� */
		private PIECE piece = PIECE.NONE;
		
		private Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
		
		/**
		 * �F + x + "" + y
		 */
		public String toString() {
			return piece.colorString + x + "" + y;
		}
	}	

	/** �}�X��� */
	private List<Cell> cellList = new ArrayList<Cell>();

	/**
	 * �R���X�g���N�^
	 */
	public Board() {
		create();	
		
		//��̏����z�u
		setPiece(PIECE.BLACK, getCell(3,3));
		setPiece(PIECE.BLACK, getCell(4,4));
		setPiece(PIECE.WHITE, getCell(3,4));
		setPiece(PIECE.WHITE, getCell(4,3));
	}

	/**
	 * �w����W�̃Z���̎擾
	 * @param x
	 * @param y
	 * @return �͈͊O��null
	 */
	public Cell getCell(int x, int y) {
		if(x <  0 || WIDTH <= x) {
			return null;
		}
		if(y <  0 || HEIGHT <= y) {
			return null;
		}
		return cellList.get(x + (y * HEIGHT));
	}	
	
	/**
	 * �Z����A�����ă{�[�h�̌`�ɂ���
	 */
	private void create() {
		//���W�̐ݒ�
		for(int i = 0;i < WIDTH * HEIGHT;i++) {
			Cell cell = new Cell(i / HEIGHT, i % WIDTH);
			cellList.add(cell);
			docking(i, cell);
		}
	}
	
	/**
	 * �}�X���m�̘A��
	 * ���炩���ߗׂ荇���}�X���m���Ȃ��Ă���
	 * @param i ���X�g���̔ԍ�
	 * @param cell �A������N�_�Z��
	 */
	private void docking(int i, Cell cell) {
		//���E
		if(i % WIDTH > 0) {
			docking(cell, DIRECTION.LEFT, cellList.get(i - 1), DIRECTION.RIGHT);
		}
		//�㉺
		if(i >= WIDTH) {
			docking(cell, DIRECTION.TOP, cellList.get(i - WIDTH), DIRECTION.BOTTOM);
		}
		//����ƉE��
		if(i % WIDTH > 0 && i >= WIDTH) {
			docking(cell, DIRECTION.TOP_L, cellList.get(i - WIDTH - 1), DIRECTION.BOTTOM_R);
		}
		//�E��ƍ���
		if(i % WIDTH <= WIDTH - 1 && i >= WIDTH) {
			docking(cell, DIRECTION.TOP_R, cellList.get(i - WIDTH + 1), DIRECTION.BOTTOM_L);
		}
	}
	
	/**
	 *�@�}�X���m�̘A��
	 * @param cellA �}�XA
	 * @param dirA �}�XA�̐ڑ���
	 * @param cellB �}�XB
	 * @param dirB�@�}�XB�̐ڑ���
	 */
	private void docking(Cell cellA, DIRECTION dirA, 
			Cell cellB, DIRECTION dirB) {
		cellA.AroundMap.put(dirA, cellB);
		cellB.AroundMap.put(dirB, cellA);
	}

	/**
	 * ��̔z�u
	 * @param piece �z�u�����
	 * @param cell �z�u�}�X
	 */
	public void setPiece(PIECE piece, Cell cell) {
		cell.piece = piece;
		reverse(piece, cell);
		log = log + cell.toString();
	}
	
	/**
	 * �u����ꏊ�̎擾
	 * @param piece�@�Ώۂ̋�
	 * @return �u����ꏊ�̃��X�g
	 */
	public List<Cell> getReverse(PIECE piece){
		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for(Cell cell : cellList) {
			if(cell.piece != PIECE.NONE) {
				continue;
			}
			if(countReverse(piece, cell) > 0) {
				list.add(cell);
			}
		}
		return list;
	}
	
	/**
	 *�@�߂���閇��(�S����)
	 * @param player �Ώۂ̐F
	 * @param cell �Ώۂ̃}�X
	 * @return
	 */
	public int countReverse(PIECE piece, Cell cell) {
		int cnt = 0;
		
		for(DIRECTION dir : cell.AroundMap.keySet()) {
			cnt += countReverse(piece, cell, dir);
		}
		return cnt;
	}
	
	/**
	 * �߂���閇��
	 * @param piece�@�Ώۂ̋�
	 * @param cell �Ώۂ̃}�X
	 * @param dir �߂����Ă�������
	 * @return �߂���閇��
	 */
	public int countReverse(PIECE piece, Cell cell, DIRECTION dir) {
		int cnt = 0;
		while(cell.AroundMap.containsKey(dir)) {
			cell = cell.AroundMap.get(dir);
			if(cell.piece == PIECE.NONE) {
				return 0;
			}
			if(cell.piece == piece) {
				return cnt;
			}
			cnt++;
		}		
		return 0;
	}

	/**
	 * �S�������߂���
	 * @param piece �Ώۂ̋�
	 * @param pos�@�Ώۂ̃}�X
	 */
	public void reverse(PIECE piece, Cell cell) {

		for(DIRECTION dir : cell.AroundMap.keySet()) {
			int cnt = countReverse(piece, cell, dir);
			if(cnt > 0) {
				reverse(piece, cell, dir);
			}
		}
	}	
	
	/**
	 * �߂���
	 * @param piece�@�Ώۂ̋�
	 * @param cell �Ώۂ̃}�X
	 * @param dir �߂����Ă�������
	 */
	private void reverse(PIECE piece, Cell cell, DIRECTION dir) {	

		while(cell.AroundMap.containsKey(dir)) {
			cell = cell.AroundMap.get(dir);
			if(cell.piece == piece) {
				return;
			}
			cell.piece = piece;
		}				
	}
	
	/**
	 * �`��
	 */
	public void draw() {
		Drawer.draw(" 12345678");//�c�̗�̔ԍ�		
		for(int y = 0;y < HEIGHT;y++) {
			String str = "" + (y + 1);//���̗�̔ԍ�
			for(int x = 0;x < WIDTH;x++) {
				
				str = str + getCell(x, y).piece.colorString;
			}
			Drawer.draw(str);//1�s�Â�
		}		
	}

	/**
	 * ����
	 * @return�@���ʂ̕�����
	 */
	public String createResult() {
		long b = cellList.stream().filter(cell -> cell.piece == PIECE.BLACK).count();
		long w = cellList.stream().filter(cell -> cell.piece == PIECE.WHITE).count();
		
		return "���� B:" + b + " W:" + w;
	}
	
	/**
	 * �����Ă�����Ԃ�
	 * @return ����������NONE
	 */
	public PIECE getWin() {
		long b = cellList.stream().filter(cell -> cell.piece == PIECE.BLACK).count();
		long w = cellList.stream().filter(cell -> cell.piece == PIECE.WHITE).count();
		
		if(b > w) {
			return PIECE.BLACK;
		}
		if(b < w) {
			return PIECE.WHITE;
		}
		return PIECE.NONE;
	}
	
	/**
	 * ���O�̌��ʂ𕶎���ŕԂ�
	 * @return
	 */
	public String getLog() {
		return log;
	}		
}
