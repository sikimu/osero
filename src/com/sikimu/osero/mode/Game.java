
package com.sikimu.osero.mode;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;

/**
 * �Q�[����
 * @author sikimu
 *
 */
public class Game extends Mode {
	
	/** ��� **/
	private Thinking move;
	
	/** �Q�[���Ŏg�p����{�[�h */
	private Board board;
	
	/** ��� */
	private Thinking firstThinking;
	
	/** ��� */
	private Thinking secondThinking;
	
	/**
	 * �R���X�g���N�^
	 * @param first ���
	 * @param second�@���
	 */
	public Game(Thinking first, Thinking second) {
		
		firstThinking = first;
		secondThinking = second;
		
		//�{�[�h�Ƀv���C���[��set����
		board = new Board();
		
		move = firstThinking;
	}
	
	@Override
	public void draw() {
		board.draw();
	}

	@Override
	public Mode update() {
		
		Thinking next;// ���̎v�l
		
		Cell cell = move.think(board);
		board.setPiece(move.getPiece(), cell);
		next = move == firstThinking ? secondThinking : firstThinking;

		if(board.getReverse(next.getPiece()).size() == 0) {//���肪�߂���Ȃ�
			if(board.getReverse(move.getPiece()).size() == 0) {// �ǂ�����߂���Ȃ��̂ŏI��
				return new Result(board);
			}	
			return this;
		}		
		move = next;
		return this;
	}

}
