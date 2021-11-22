package com.sikimu.osero.player;

import com.sikimu.osero.abst.Color;
import com.sikimu.osero.abst.Thinking;

/**
 * オセロをやる人
 * 人間だったりCPUだったり
 * @author sikimu
 *
 */
public class Player {

	/** 色 */
	private Color color;
	
	/** 使用している思考 */
	private Thinking thinking;
	
	/**
	 * コンストラクタ
	 * @param color 使用する色
	 * @param thinking　使用する思考
	 */
	public Player(Color color, Thinking thinking) {
		this.color = color;
		this.thinking = thinking;
	}
	
	
	/**
	 * 思考結果を返す
	 * @return 配置する番号
	 */
	public int think() {
		return thinking.think();
	}


	/**
	 * プレイヤーの色の取得
	 * @return プレイヤーの色
	 */
	public Color getColor() {
		return color;
	}
}
