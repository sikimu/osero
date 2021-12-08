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
 * �����m����
 * @author sikimu
 *
 */
public class LosingConfirmedInfo {
	
	/** ���ĂȂ��������X�g */
	private static Set<String> confirmedList = new HashSet<String>();
	
	static {
		confirmedList.addAll(Arrays.asList(LosingData.data));
	}
	

	/**
	 * �����̒ǉ�
	 * @param piece ��������
	 * @param log ���O
	 */
	public static void add(PIECE piece, String log) {
		
		//1�O�̎����̐F+2�܂ō��
		log = log.substring(0, log.lastIndexOf(piece.colorString) + 3);
		
		log = piece.colorString + log;
		
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
	 * @param log ���O
	 * @param list �z�u���
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
		//0���Ȃ炱�̎��_�ŕ����m��Ƃ���
		if(list.size() == 0) {
			add(piece, log);
		}
	}

	/**
	 * ���O�̏o��
	 * @param piece
	 */
	public static void printLog() {
		
		for(String log : confirmedList) {
			System.out.println("\"" + log + "\",");
		}
	}
}
