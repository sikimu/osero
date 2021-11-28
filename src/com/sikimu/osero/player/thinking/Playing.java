package com.sikimu.osero.player.thinking;

import com.sikimu.osero.Controller;
import com.sikimu.osero.Drawer;
import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.BoardPos;
import com.sikimu.osero.item.piece.Piece.COLOR;

/**
 * ユーザー操作で行う
 * @author sikimu
 *
 */
public class Playing extends Thinking {

	public Playing(COLOR color) {
		super(color);
	}

	@Override
	public BoardPos think(Board board) {
		
		BoardPos pos = null;
		
		do {
			pos = input(board);
		}while(pos == null);
		
		return pos;
	}

	/**
	 * 入力し直し対応入力処理
	 * @return 配置箇所
	 */
	private BoardPos input(Board board) {
		
		try {
			int no = Controller.inputInt();
			
			//10の位が横,1の位が縦
			BoardPos pos = new BoardPos((no / 10) - 1, (no % 10) - 1);
			
			if(board.isSetPiece(pos) == false) {
				Drawer.draw("おけません");
				return null;
			}
			if(board.countReverse(getColor(), pos) == 0) {
				Drawer.draw("めくれません");
				return null;
			}

			return pos;
		} catch (Exception e) {
			Drawer.draw("入力が不正です。横縦の順で数値を入力してください。例：右上の角は91");
			return null;
		}
	}
}
