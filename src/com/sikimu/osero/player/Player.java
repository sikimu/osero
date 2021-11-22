package com.sikimu.osero.player;

import com.sikimu.osero.abst.Color;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;

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
	 * @param board 現在のボード
	 * @return 配置する番号
	 */
	public BoardPos think(Board board) {
		return thinking.think(board);
	}


	/**
	 * プレイヤーの色の取得
	 * @return プレイヤーの色
	 */
	public Color getColor() {
		return color;
	}
}
