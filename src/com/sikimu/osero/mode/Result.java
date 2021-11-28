package com.sikimu.osero.mode;

import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.item.Board;

/**
 * 試合結果
 * @author sikimu
 *
 */
public class Result extends Mode {

	/** ボード */
	private Board board;
	
	/**
	 * コンストラクタ
	 * @param board　ボード
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
