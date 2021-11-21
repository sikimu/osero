package com.sikimu.osero.mode;

import java.util.ArrayList;

import com.sikimu.osero.item.Board;
import com.sikimu.osero.player.Player;
import com.sikimu.osero.player.thinking.Playing;

public class Game extends Mode {

	/** ゲームで使用するボード */
	private Board board;
	
	/** プレイヤーリスト(プレイする順番に入っている) */
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
