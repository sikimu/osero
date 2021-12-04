package com.sikimu.osero.abst;

import java.util.ArrayList;
import java.util.List;

import com.sikimu.osero.item.Board;
import com.sikimu.osero.item.Board.Cell;
import com.sikimu.osero.item.Board.PIECE;
import com.sikimu.osero.item.ThinkingBoard;

/**
 * �v�l�̊��N���X
 * ������Ɍ��ʂ����߂邩
 * @author sikimu
 *
 */
public abstract class Thinking {
	
	/** �����̋�@*/
	private PIECE piece;
	
	/** �z�u���@*/
	private List<Cell> setLog = new ArrayList<Board.Cell>();
	
	public Thinking(PIECE piece) {
		this.piece = piece;
	}
	
	/**
	 * ��̔z�u����ꏊ�̌���
	 * @param board
	 */
	public Cell getCell(ThinkingBoard board) {
		Cell cell = think(board);
		setLog.add(cell);
		return cell;
	}
	
	/**
	 * �v�l
	 * @param player �v�l���g�p����F
	 * @param board ���݂̃{�[�h
	 * @return �z�u�ӏ�
	 */
	protected abstract Cell think(ThinkingBoard board);

	public PIECE getPiece() {
		return piece;
	}

	/**
	 * ���O�̌��ʂ𕶎���ŕԂ�
	 * @return
	 */
	public String getLog() {
		String str = "";
		for (Cell cell : setLog) {
			str = str + cell.toString();
		}
		return str;
	}
	
	
}
