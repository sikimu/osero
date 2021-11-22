package com.sikimu.osero.mode;

import java.util.ArrayList;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.piece.color.Black;
import com.sikimu.osero.item.piece.color.White;
import com.sikimu.osero.player.Player;
import com.sikimu.osero.player.thinking.Playing;

public class Game extends Mode {

	/** �Q�[���Ŏg�p����{�[�h */
	private Board board;
	
	/** �v���C���[���X�g(�v���C���鏇�Ԃɓ����Ă���) */
	private ArrayList<Player> playerList;
	
	public Game() {
		playerList = new ArrayList<Player>();
		
		Player first = new Player(new Black(), new Playing());
		Player second = new Player(new White(), new Playing());
		
		playerList.add(first);
		playerList.add(second);
		
		board = new Board(playerList);
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
