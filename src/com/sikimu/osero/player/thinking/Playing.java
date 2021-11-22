package com.sikimu.osero.player.thinking;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;

/**
 * ユーザー操作で行う
 * @author sikimu
 *
 */
public class Playing extends Thinking {

	@Override
	public BoardPos think(Board board) {
		
		return input(board);
	}

	/**
	 * 入力し直し対応入力処理
	 * @return 配置箇所
	 */
	private BoardPos input(Board board) {
		
		try {
			int no = Controller.inputInt();
			
			//10の位が横
			int x = no / 10 - 1;
			int y = no % 10 - 1;
			
			if(x <  0 || board.getWidth() <= x) {
				throw new Exception();
			}
			if(y <  0 || board.getHeight() <= y) {
				throw new Exception();
			}
			
			return new BoardPos(x, y);
		} catch (Exception e) {
			Drawer.draw("入力が不正です。横縦の順で数値を入力してください。例：右上の角は91");
			return input(board);
		}
	}
}
