package com.sikimu.osero.item;

import java.util.ArrayList;
import java.util.List;

/**
 * �v�l���₷���g���@�\
 * @author sikimu
 *
 */
public class ThinkingBoard extends Board {
	
	/**
	 * �p�̃}�X��Ԃ�
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
