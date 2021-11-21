package com.sikimu.osero.player;

import com.sikimu.osero.player.thinking.Thinking;

/**
 * オセロをやる人
 * 人間だったりCPUだったり
 * @author sikimu
 *
 */
public class Player {

	/** 使用している思考 */
	private Thinking thinking;
	
	/**
	 * コンストラクタ
	 * @param thinking　使用する思考
	 */
	public Player(Thinking thinking) {
		this.thinking = thinking;
	}
	
	
	/**
	 * 思考結果を返す
	 * @return
	 */
	public int think() {
		return thinking.think();
	}
}
