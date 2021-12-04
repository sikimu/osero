
package com.sikimu.osero.mode;

import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.item.ThinkingBoard;

/**
 * �Q�[����
 * @author sikimu
 *
 */
public class Game extends Mode {
	
	/** ��� **/
	private Thinking move;
	
	/** �Q�[���Ŏg�p����{�[�h */
	private ThinkingBoard board;
	
	/** ��� */
	private Thinking brackThinking;
	
	/** ��� */
	private Thinking whiteThinking;

	/**
	 * �R���X�g���N�^
	 * @param thinkB ���
	 * @param thinkW�@���
	 */
	public Game(Thinking thinkB, Thinking thinkW) {
		
		brackThinking = thinkB;
		whiteThinking = thinkW;

		//�{�[�h�Ƀv���C���[��set����
		board = new ThinkingBoard();
		
		move = brackThinking;
	}
	
	@Override
	public void draw() {
		board.draw();
	}

	@Override
	public Mode update() {
		
		Thinking next;// ���̎v�l
		
		Cell cell = move.getCell(board);
		board.setPiece(move.getPiece(), cell);
		next = move == brackThinking ? whiteThinking : brackThinking;

		if(board.getReverse(next.getPiece()).size() == 0) {//���肪�߂���Ȃ�
			if(board.getReverse(move.getPiece()).size() == 0) {// �ǂ�����߂���Ȃ��̂ŏI��
				setLosing();
				return new Result(board);
			}	
			return this;
		}		
		move = next;
		return this;
	}

	/**
	 * ������o�^
	 */
	private void setLosing() {
		PIECE win = board.getWin();
		switch(win){
			case BLACK:
				LosingConfirmedInfo.add(whiteThinking);
				break;
			case WHITE:
				LosingConfirmedInfo.add(brackThinking);
				break;
			case NONE:
				LosingConfirmedInfo.add(brackThinking);
				LosingConfirmedInfo.add(whiteThinking);
				break;
		}
	}
}
