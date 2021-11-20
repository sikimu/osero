package com.sikimu.osero.mode;

/**
 * 状態ごとの制御クラスの抽象クラス
 * @author sikimu
 *
 */
public abstract class Mode {
	
	/**
	 * 描画
	 */
	abstract public void draw();		
	
	/**
	 * 更新
	 * @return 更新後の制御クラス(変更なしの場合自分自身)
	 */
	abstract public Mode update();
}
