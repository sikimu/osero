package com.sikimu.osero.mode;

import com.sikimu.osero.item.Board;

public class Game extends Mode {

	/** ゲームで使用するボード */
	private Board board;
	
	public Game() {
		board = new Board();
	}
	
	@Override
	public void draw() {
		board.draw();
	}

	@Override
	public Mode update() {
		// TODO Auto-generated method stub
		return null;
	}

}
