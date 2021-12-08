package com.sikimu.osero.abst;

import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;

/**
 * �v�l�̊��N���X
 * ������Ɍ��ʂ����߂邩
 * @author sikimu
 *
 */
public abstract class Thinking {
	
	/** �����̋�@*/
	private PIECE piece;
	
	public Thinking(PIECE piece) {
		this.piece = piece;
	}
	
	/**
	 * ��̔z�u����ꏊ�̌���
	 * @param board
	 */
	public Cell getCell(Board board) {
		Cell cell = think(board);
		return cell;
	}
	
	/**
	 * �v�l
	 * @param player �v�l���g�p����F
	 * @param board ���݂̃{�[�h
	 * @return �z�u�ӏ�
	 */
	protected abstract Cell think(Board board);

	public PIECE getPiece() {
		return piece;
	}
	
}
