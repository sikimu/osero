
package com.sikimu.osero.mode;

import com.sikimu.osero.abst.Mode;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.player.Player;

/**
 * �Q�[����
 * @author sikimu
 *
 */
public class Game extends Mode {

	/** ��� **/
	private int move;
	
	/** �Q�[���Ŏg�p����{�[�h */
	private Board board;
	
	/** �v���C���[���X�g(�v���C���鏇�Ԃɓ����Ă���) */
	private Player[] players;
	
	public Game(Player... players) {
		this.players = players;	

		//�{�[�h�Ƀv���C���[��set����
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
		
		//���̃v���C���[�֐؂�ւ�
		if(++move >= players.length) {
			move = 0;
		}
		
		return this;
	}

}
