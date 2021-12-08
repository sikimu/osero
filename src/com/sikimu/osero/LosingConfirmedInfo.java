package com.sikimu.osero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sikimu.osero.data.confirmed.LosingData;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;

/**
 * 負け確定情報
 * @author sikimu
 *
 */
public class LosingConfirmedInfo {
	
	/** 勝てなかったリスト */
	private static Set<String> confirmedList = new HashSet<String>();
	
	static {
		confirmedList.addAll(Arrays.asList(LosingData.data));
	}
	

	/**
	 * 負けの追加
	 * @param piece 負けた駒
	 * @param log ログ
	 */
	public static void add(PIECE piece, String log) {
		
		//1つ前の自分の色+2まで削る
		log = log.substring(0, log.lastIndexOf(piece.colorString) + 3);
		
		log = piece.colorString + log;
		
		List<String> deleteList = new ArrayList<String>();
		for(String confirmed : confirmedList) {
			//不要なものを削除
			if(confirmed.startsWith(log)) {
				deleteList.add(confirmed);
			}
			//追加不要
			if(log.startsWith(confirmed)) {
				return;
			}
		}
		confirmedList.removeAll(deleteList);				
		confirmedList.add(log);		
	}
	
	/**
	 * 負け確定のセルを削除
	 * @param log ログ
	 * @param list 配置候補
	 * @return
	 */
	public static void deleteConfirmed(PIECE piece, String log, ArrayList<Cell> list){
		List<Cell> deleteList = new ArrayList<Cell>();

		for(Cell cell : list) {
			String code = piece.colorString + cell.getX() + "" + cell.getY();
			if(confirmedList.contains(piece.colorString + log + code)) {
				deleteList.add(cell);
			}
		}
		list.removeAll(deleteList);
		//0件ならこの時点で負け確定とする
		if(list.size() == 0) {
			add(piece, log);
		}
	}

	/**
	 * ログの出力
	 * @param piece
	 */
	public static void printLog() {
		
		for(String log : confirmedList) {
			System.out.println("\"" + log + "\",");
		}
	}
}
