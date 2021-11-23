package com.sikimu.osero.abst;

import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.player.Player;

/**
 * �v�l�̊��N���X
 * ������Ɍ��ʂ����߂邩
 * @author sikimu
 *
 */
public abstract class Thinking {
	
	/**
	 * �v�l
	 * @param player �v�l���g�p����v���C���[
	 * @param board ���݂̃{�[�h
	 * @return �z�u�ӏ�
	 */
	public abstract BoardPos think(Player player, Board board);
}
