package com.sikimu.osero.mode;

import java.util.ArrayList;

import com.sikimu.osero.item.Board;
import com.sikimu.osero.player.Player;
import com.sikimu.osero.player.thinking.Playing;

public class Game extends Mode {

	/** �Q�[���Ŏg�p����{�[�h */
	private Board board;
	
	/** �v���C���[���X�g(�v���C���鏇�Ԃɓ����Ă���) */
	private ArrayList<Player> playerList;
	
	public Game() {
		board = new Board();
		playerList = new ArrayList<Player>();
		playerList.add(new Player(new Playing()));
	}
	
	@Override
	public void draw() {
		board.draw();
	}

	@Override
	public Mode update() {
		playerList.get(0).think();
		return this;
	}

}
