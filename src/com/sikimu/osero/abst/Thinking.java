package com.sikimu.osero.abst;

import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;

/**
 * �v�l�̊��N���X
 * ������Ɍ��ʂ����߂邩
 * @author sikimu
 *
 */
public abstract class Thinking {
	
	/**
	 * �v�l
	 * @param board ���݂̃{�[�h
	 * @return �z�u�ӏ�
	 */
	public abstract BoardPos think(Board board);
}
