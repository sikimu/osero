
package com.sikimu.osero.mode;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.player.Player;

/**
 * ゲーム中
 * @author sikimu
 *
 */
public class Game extends Mode {

	/** 手番 **/
	private int move;
	
	/** ゲームで使用するボード */
	private Board board;
	
	/** プレイヤーリスト(プレイする順番に入っている) */
	private Player[] players;
	
	public Game(Player... players) {
		this.players = players;	

		//ボードにプレイヤーをsetする
		board = new Board(players);
		
		move = 0;
	}
	
	@Override
	public void draw() {
		board.draw();
	}

	@Override
	public Mode update() {
		
		Player p = players[move];
		board.setPiece(p, p.think(board));
		
		//次のプレイヤーへ切り替え
		if(++move >= players.length) {
			move = 0;
		}
		
		return this;
	}

}
