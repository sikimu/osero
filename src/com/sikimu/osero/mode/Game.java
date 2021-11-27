
package com.sikimu.osero.mode;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.item.piece.Piece.COLOR;

/**
 * �Q�[����
 * @author sikimu
 *
 */
public class Game extends Mode {
	
	/** ��� **/
	private COLOR moveColor;
	
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
		
		moveColor = COLOR.BLACK;
	}
	
	@Override
	public void draw() {
		board.draw();
	}

	@Override
	public Mode update() {
		
		COLOR next;// ���̐F
		
		if(moveColor == COLOR.BLACK) {
			BoardPos pos = firstThinking.think(COLOR.BLACK, board);
			board.setPiece(COLOR.BLACK, pos);
			next = COLOR.WHITE;
		}
		else {
			BoardPos pos = secondThinking.think(COLOR.WHITE, board);
			board.setPiece(COLOR.WHITE, pos);
			next = COLOR.BLACK;
		}
		if(board.getReverse(next).size() > 0) {//�߂����̂Ŏ���
			moveColor = next;
			return this;
		}
		else if(board.getReverse(moveColor).size() == 0) {// �ǂ�����߂���Ȃ��̂ŏI��
			return new Title();
		}		
		
		return this;//�ēx�����̎��
	}

}
