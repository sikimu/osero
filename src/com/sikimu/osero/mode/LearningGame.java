package com.sikimu.osero.mode;

import com.sikimu.osero.LosingConfirmedInfo;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.player.thinking.Computing;

/**
 * �Q�[����(Learning��p)
 * ���������N���X�̍���������������
 * @author sikimu
 *
 */
public class LearningGame extends Mode {
	
	/** ��� **/
	private Thinking move;
	
	/** �Q�[���Ŏg�p����{�[�h */
	private Board board;
	
	/**�@�Ē���p */
	private Board reBoard;
	
	/**�@�Ē���p */
	private Thinking reMove;
	
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
	 * @param m ���
	 */
	public LearningGame(Board board, Thinking thinkB, Thinking thinkW, Thinking m, int repeat) {
		
		this.repeat = repeat;
		
		brackThinking = thinkB;
		whiteThinking = thinkW;

		//�{�[�h�Ƀv���C���[��set����
		this.board = board;
		reBoard = Board.createCopy(board);
		
		move = m;
	}
	
	@Override
	public void draw() {
		//board.draw();
	}

	@Override
	public Mode update() {
		
		if(board.getCount(PIECE.NONE) == 10) {
			reBoard = Board.createCopy(board);
			reMove = move;
		}
		
		Thinking next;// ���̎v�l
		
		Cell cell = move.getCell(board);
		if(cell == null) {
			//�A���Đ�(CPU�̂�)
			if(repeat-- > 0) {
				if(repeat % 10000 == 0) {
					System.out.println("repeat=" + repeat);
				}
				return new LearningGame(new Board(), brackThinking, whiteThinking, brackThinking, repeat);
			}
			return new Result(board);
		}
		board.setPiece(move.getPiece(), cell);
		next = move == brackThinking ? whiteThinking : brackThinking;

		if(board.getReverse(next.getPiece()).size() == 0) {//���肪�߂���Ȃ�
			if(board.getReverse(move.getPiece()).size() == 0) {// �ǂ�����߂���Ȃ��̂ŏI��
				setLosing();
				//�A���Đ�(CPU�̂�)
				if(repeat-- > 0) {
					if(repeat % 10000 == 0) {
						System.out.println("repeat=" + repeat);
					}
					return new LearningGame(reBoard, brackThinking, whiteThinking, reMove, repeat);
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
