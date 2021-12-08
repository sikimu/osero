package com.sikimu.osero.mode;

import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.item.ThinkingBoard;
import com.sikimu.osero.player.thinking.Computing;

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

	/** �J��Ԃ��� */
	private int repeat;
	
	/**
	 * �R���X�g���N�^
	 * @param thinkB ���
	 * @param thinkW�@���
	 */
	public Game(Thinking thinkB, Thinking thinkW, int repeat) {
		
		this.repeat = repeat;
		
		brackThinking = thinkB;
		whiteThinking = thinkW;

		//�{�[�h�Ƀv���C���[��set����
		board = new ThinkingBoard();
		
		move = brackThinking;
	}
	
	@Override
	public void draw() {
		
		//�A�풆�͕\�����Ȃ�
		if(repeat > 0) {
			return;
		}
		
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
				//�A���Đ�(CPU�̂�)
				if(repeat-- > 0) {
					Thinking first = new Computing(PIECE.BLACK);
					Thinking second = new Computing(PIECE.WHITE);
					return new Game(first, second, repeat);
				}
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
				LosingConfirmedInfo.add(PIECE.WHITE, board.getLog());
				break;
			case WHITE:
				LosingConfirmedInfo.add(PIECE.BLACK, board.getLog());
				break;
			case NONE:
				LosingConfirmedInfo.add(PIECE.BLACK, board.getLog());
				LosingConfirmedInfo.add(PIECE.WHITE, board.getLog());
				break;
		}
	}
}
