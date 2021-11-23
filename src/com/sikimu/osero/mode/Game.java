package com.sikimu.osero.mode;

import java.util.ArrayList;
import java.util.Iterator;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.item.piece.color.Black;
import com.sikimu.osero.item.piece.color.White;
import com.sikimu.osero.player.Player;
import com.sikimu.osero.player.thinking.Playing;

public class Game extends Mode {

	/** 手番 **/
	private int move;
	
	/** ゲームで使用するボード */
	private Board board;
	
	/** プレイヤーリスト(プレイする順番に入っている) */
	private ArrayList<Player> playerList;
	
	public Game() {
		playerList = new ArrayList<Player>();
		
		//プレイヤーの作成
		Player first = new Player(new Black(), new Playing());
		Player second = new Player(new White(), new Playing());		
		playerList.add(first);
		playerList.add(second);
		
		//ボードにプレイヤーをsetする
		board = new Board(playerList);
		
		move = 0;
	}
	
	@Override
	public void draw() {
		board.draw();
	}

	@Override
	public Mode update() {
		BoardPos pos = playerList.get(move).think(board);
		board.setPiece(playerList.get(move), pos);
		
		//次のプレイヤーへ切り替え
		if(++move >= playerList.size()) {
			move = 0;
		}
		
		return this;
	}

}
