package com.sikimu.osero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;

/**
 * 負け確定情報
 * @author sikimu
 *
 */
public class LosingConfirmedInfo {
	
	/** 勝てなかったリスト */
	private static Map<PIECE, List<String>> confirmedMap;
	
	static {
		confirmedMap = new HashMap<PIECE, List<String>>();
		confirmedMap.put(PIECE.BLACK, new ArrayList<String>());
		confirmedMap.put(PIECE.WHITE, new ArrayList<String>());
		confirmedMap.put(PIECE.NONE, new ArrayList<String>());
	}
	
	/**
	 * 負け情報の追加
	 * @param loser
	 * @param setedPieceList
	 */
	public static void add(Thinking thinking) {
		confirmedMap.get(thinking.getPiece()).add(thinking.getLog());
	}
	
	/**
	 * 負け確定のセルを削除
	 * @param thinking 思考
	 * @param list 配置候補
	 * @return
	 */
	public static void deleteConfirmed(Thinking thinking, ArrayList<Cell> list){
		List<Cell> deleteList = new ArrayList<Cell>();
		
		List<String> confirmedList = confirmedMap.get(thinking.getPiece());
		String log = thinking.getLog();
		for(Cell cell : list) {
			if(confirmedList.contains(log + cell.toString())) {
				deleteList.add(cell);
			}
		}
		list.removeAll(deleteList);
		//0件ならこの時点で負け確定とする
		confirmedList.add(log);
	}
}
