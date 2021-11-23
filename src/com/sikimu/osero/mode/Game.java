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

	/** ��� **/
	private int move;
	
	/** �Q�[���Ŏg�p����{�[�h */
	private Board board;
	
	/** �v���C���[���X�g(�v���C���鏇�Ԃɓ����Ă���) */
	private ArrayList<Player> playerList;
	
	public Game() {
		playerList = new ArrayList<Player>();
		
		//�v���C���[�̍쐬
		Player first = new Player(new Black(), new Playing());
		Player second = new Player(new White(), new Playing());		
		playerList.add(first);
		playerList.add(second);
		
		//�{�[�h�Ƀv���C���[��set����
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
		
		//���̃v���C���[�֐؂�ւ�
		if(++move >= playerList.size()) {
			move = 0;
		}
		
		return this;
	}

}
