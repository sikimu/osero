package com.sikimu.osero.abst;

import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.item.piece.Piece.COLOR;

/**
 * �v�l�̊��N���X
 * ������Ɍ��ʂ����߂邩
 * @author sikimu
 *
 */
public abstract class Thinking {
	
	/** �����̐F�@*/
	private COLOR color;
	
	public Thinking(COLOR color) {
		this.color = color;
	}
	
	/**
	 * �v�l
	 * @param player �v�l���g�p����F
	 * @param board ���݂̃{�[�h
	 * @return �z�u�ӏ�
	 */
	public abstract BoardPos think(Board board);

	public COLOR getColor() {
		return color;
	}
}
