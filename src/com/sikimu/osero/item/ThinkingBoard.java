package com.sikimu.osero.item;

import java.util.ArrayList;
import java.util.List;

/**
 * 思考しやすい拡張機能
 * @author sikimu
 *
 */
public class ThinkingBoard extends Board {
	
	/**
	 * 角のマスを返す
	 * @return
	 */
	public List<Cell> getCorner(){
		ArrayList<Cell> list = new ArrayList<Board.Cell>();
		
		list.add(getCell(0, 0));
		list.add(getCell(0, WIDTH - 1));
		list.add(getCell(0, WIDTH - 1));
		list.add(getCell(0, WIDTH - 1));
		
		return list;
	}
}
