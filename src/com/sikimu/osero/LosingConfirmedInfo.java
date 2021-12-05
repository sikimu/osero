package com.sikimu.osero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sikimu.osero.abst.Thinking;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;

/**
 * �����m����
 * @author sikimu
 *
 */
public class LosingConfirmedInfo {
	
	/** ���ĂȂ��������X�g */
	private static Map<PIECE, List<String>> confirmedMap;
	
	static {
		confirmedMap = new HashMap<PIECE, List<String>>();
		confirmedMap.put(PIECE.BLACK, new ArrayList<String>());
		confirmedMap.put(PIECE.WHITE, new ArrayList<String>());
		confirmedMap.put(PIECE.NONE, new ArrayList<String>());
	}
	
	/**
	 * �������̒ǉ�
	 * @param loser
	 * @param setedPieceList
	 */
	public static void add(Thinking thinking) {
		
		List<String> confirmedList = confirmedMap.get(thinking.getPiece());
		String log = thinking.getLog();
		
		List<String> deleteList = new ArrayList<String>();
		for(String confirmed : confirmedList) {
			//�s�v�Ȃ��̂��폜
			if(confirmed.startsWith(log)) {
				deleteList.add(confirmed);
			}
			//�ǉ��s�v
			if(log.startsWith(confirmed)) {
				return;
			}
		}
		confirmedList.removeAll(deleteList);				
		confirmedList.add(log);		
	}
	
	/**
	 * �����m��̃Z�����폜
	 * @param thinking �v�l
	 * @param list �z�u���
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
		//0���Ȃ炱�̎��_�ŕ����m��Ƃ���
		if(list.size() == 0) {
			add(thinking);
		}
	}

	/**
	 * ���O�̏o��
	 * @param piece
	 */
	public static void printLog(PIECE piece) {
		
		for(String log : confirmedMap.get(piece)) {
			System.out.println(log);
		}
	}
}
