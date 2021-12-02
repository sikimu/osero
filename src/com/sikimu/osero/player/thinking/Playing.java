package com.sikimu.osero.player.thinking;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;

/**
 * ユーザー操作で行う
 * @author sikimu
 *
 */
public class Playing extends Thinking {

	public Playing(PIECE piece) {
		super(piece);
	}

	@Override
	public Cell think(Board board) {
		
		Cell cell = null;
		
		do {
			cell = input(board);
		}while(cell == null);
		
		return cell;
	}

	/**
	 * 入力し直し対応入力処理
	 * @return 配置箇所
	 */
	private Cell input(Board board) {
		
		try {
			int no = Controller.inputInt();
			
			//10の位が横,1の位が縦
			Cell cell = board.getCell((no / 10) - 1, (no % 10) - 1);
			
			if(cell == null) {
				Drawer.draw("おけません");
				return null;
			}
			if(board.countReverse(getPiece(), cell) == 0) {
				Drawer.draw("めくれません");
				return null;
			}

			return cell;
		} catch (Exception e) {
			Drawer.draw("入力が不正です。横縦の順で数値を入力してください。例：右上の角は91");
			return null;
		}
	}
}
