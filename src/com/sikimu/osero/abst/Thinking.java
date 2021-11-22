package com.sikimu.osero.abst;

import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;

/**
 * 思考の基底クラス
 * 何を基準に結果を決めるか
 * @author sikimu
 *
 */
public abstract class Thinking {
	
	/**
	 * 思考
	 * @param board 現在のボード
	 * @return 配置箇所
	 */
	public abstract BoardPos think(Board board);
}
