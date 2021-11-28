package com.sikimu.osero.mode;

import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.item.Board;

/**
 * ��������
 * @author sikimu
 *
 */
public class Result extends Mode {

	/** �{�[�h */
	private Board board;
	
	/**
	 * �R���X�g���N�^
	 * @param board�@�{�[�h
	 */
	public Result(Board board) {
		this.board = board;
	}
	
	@Override
	public void draw() {
		board.draw();
		Drawer.draw(board.createResult());
	}

	@Override
	public Mode update() {
		return new Title();
	}

}
