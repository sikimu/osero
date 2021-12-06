package com.sikimu.osero.item;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sikimu.osero.Drawer;

/**
 * オセロのボード
 * @author sikimu
 *
 */
public class Board {
	
	/** 横幅 */
	protected final static int WIDTH = 8;
	
	/** 縦幅 */
	protected final static int HEIGHT = 8;
	
	/** 配置情報　*/
	private String log = "";			
	
	/**
	 * マスからみた方向
	 * @author sikimu
	 *
	 */
	public enum DIRECTION{
		TOP,
		LEFT,
		RIGHT,
		BOTTOM,
		TOP_L,
		TOP_R,
		BOTTOM_L,
		BOTTOM_R,
	}
	
	/**
	 * 駒情報
	 * @author sikimu
	 *
	 */
	public enum PIECE{
		NONE("+"),		
		WHITE("W"),
		BLACK("B");
		
		/** 表記用文字列 */
		public final String colorString;
		
		/**
		 * コンストラクタ
		 * @param str 表記用文字
		 */
		PIECE(String str) {
			colorString = str;
		}
	}	
	
	/**
	 * マス
	 * ボード経由でのみ受け取れるようにするのでprivateで作ってgetも座標以外なし
	 * @author sikimu
	 *
	 */
	public class Cell{
		private int x;
		private int y;
		
		/**
		 * 周りのセル
		 */
		private Map<DIRECTION, Cell> AroundMap = new LinkedHashMap<Board.DIRECTION, Board.Cell>();
		
		/** 駒 */
		private PIECE piece = PIECE.NONE;
		
		private Cell(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
		
		/**
		 * 色 + x + "" + y
		 */
		public String toString() {
			return piece.colorString + x + "" + y;
		}
	}	

	/** マス情報 */
	private List<Cell> cellList = new ArrayList<Cell>();

	/**
	 * コンストラクタ
	 */
	public Board() {
		create();	
		
		//駒の初期配置
		setPiece(PIECE.BLACK, getCell(3,3));
		setPiece(PIECE.BLACK, getCell(4,4));
		setPiece(PIECE.WHITE, getCell(3,4));
		setPiece(PIECE.WHITE, getCell(4,3));
	}

	/**
	 * 指定座標のセルの取得
	 * @param x
	 * @param y
	 * @return 範囲外はnull
	 */
	public Cell getCell(int x, int y) {
		if(x <  0 || WIDTH <= x) {
			return null;
		}
		if(y <  0 || HEIGHT <= y) {
			return null;
		}
		return cellList.get(x + (y * HEIGHT));
	}	
	
	/**
	 * セルを連結してボードの形にする
	 */
	private void create() {
		//座標の設定
		for(int i = 0;i < WIDTH * HEIGHT;i++) {
			Cell cell = new Cell(i / HEIGHT, i % WIDTH);
			cellList.add(cell);
			docking(i, cell);
		}
	}
	
	/**
	 * マス同士の連結
	 * あらかじめ隣り合うマス同士をつなげておく
	 * @param i リスト内の番号
	 * @param cell 連結する起点セル
	 */
	private void docking(int i, Cell cell) {
		//左右
		if(i % WIDTH > 0) {
			docking(cell, DIRECTION.LEFT, cellList.get(i - 1), DIRECTION.RIGHT);
		}
		//上下
		if(i >= WIDTH) {
			docking(cell, DIRECTION.TOP, cellList.get(i - WIDTH), DIRECTION.BOTTOM);
		}
		//左上と右下
		if(i % WIDTH > 0 && i >= WIDTH) {
			docking(cell, DIRECTION.TOP_L, cellList.get(i - WIDTH - 1), DIRECTION.BOTTOM_R);
		}
		//右上と左下
		if(i % WIDTH <= WIDTH - 1 && i >= WIDTH) {
			docking(cell, DIRECTION.TOP_R, cellList.get(i - WIDTH + 1), DIRECTION.BOTTOM_L);
		}
	}
	
	/**
	 *　マス同士の連結
	 * @param cellA マスA
	 * @param dirA マスAの接続面
	 * @param cellB マスB
	 * @param dirB　マスBの接続面
	 */
	private void docking(Cell cellA, DIRECTION dirA, 
			Cell cellB, DIRECTION dirB) {
		cellA.AroundMap.put(dirA, cellB);
		cellB.AroundMap.put(dirB, cellA);
	}

	/**
	 * 駒の配置
	 * @param piece 配置する駒
	 * @param cell 配置マス
	 */
	public void setPiece(PIECE piece, Cell cell) {
		cell.piece = piece;
		reverse(piece, cell);
		log = log + cell.toString();
	}
	
	/**
	 * 置ける場所の取得
	 * @param piece　対象の駒
	 * @return 置ける場所のリスト
	 */
	public List<Cell> getReverse(PIECE piece){
		ArrayList<Cell> list = new ArrayList<Cell>();
		
		for(Cell cell : cellList) {
			if(cell.piece != PIECE.NONE) {
				continue;
			}
			if(countReverse(piece, cell) > 0) {
				list.add(cell);
			}
		}
		return list;
	}
	
	/**
	 *　めくれる枚数(全方向)
	 * @param player 対象の色
	 * @param cell 対象のマス
	 * @return
	 */
	public int countReverse(PIECE piece, Cell cell) {
		int cnt = 0;
		
		for(DIRECTION dir : cell.AroundMap.keySet()) {
			cnt += countReverse(piece, cell, dir);
		}
		return cnt;
	}
	
	/**
	 * めくれる枚数
	 * @param piece　対象の駒
	 * @param cell 対象のマス
	 * @param dir めくっていく方向
	 * @return めくれる枚数
	 */
	public int countReverse(PIECE piece, Cell cell, DIRECTION dir) {
		int cnt = 0;
		while(cell.AroundMap.containsKey(dir)) {
			cell = cell.AroundMap.get(dir);
			if(cell.piece == PIECE.NONE) {
				return 0;
			}
			if(cell.piece == piece) {
				return cnt;
			}
			cnt++;
		}		
		return 0;
	}

	/**
	 * 全方向をめくる
	 * @param piece 対象の駒
	 * @param pos　対象のマス
	 */
	public void reverse(PIECE piece, Cell cell) {

		for(DIRECTION dir : cell.AroundMap.keySet()) {
			int cnt = countReverse(piece, cell, dir);
			if(cnt > 0) {
				reverse(piece, cell, dir);
			}
		}
	}	
	
	/**
	 * めくる
	 * @param piece　対象の駒
	 * @param cell 対象のマス
	 * @param dir めくっていく方向
	 */
	private void reverse(PIECE piece, Cell cell, DIRECTION dir) {	

		while(cell.AroundMap.containsKey(dir)) {
			cell = cell.AroundMap.get(dir);
			if(cell.piece == piece) {
				return;
			}
			cell.piece = piece;
		}				
	}
	
	/**
	 * 描画
	 */
	public void draw() {
		Drawer.draw(" 12345678");//縦の列の番号		
		for(int y = 0;y < HEIGHT;y++) {
			String str = "" + (y + 1);//横の列の番号
			for(int x = 0;x < WIDTH;x++) {
				
				str = str + getCell(x, y).piece.colorString;
			}
			Drawer.draw(str);//1行づつ
		}		
	}

	/**
	 * 結果
	 * @return　結果の文字列
	 */
	public String createResult() {
		long b = cellList.stream().filter(cell -> cell.piece == PIECE.BLACK).count();
		long w = cellList.stream().filter(cell -> cell.piece == PIECE.WHITE).count();
		
		return "結果 B:" + b + " W:" + w;
	}
	
	/**
	 * 勝っている駒を返す
	 * @return 引き分けはNONE
	 */
	public PIECE getWin() {
		long b = cellList.stream().filter(cell -> cell.piece == PIECE.BLACK).count();
		long w = cellList.stream().filter(cell -> cell.piece == PIECE.WHITE).count();
		
		if(b > w) {
			return PIECE.BLACK;
		}
		if(b < w) {
			return PIECE.WHITE;
		}
		return PIECE.NONE;
	}
	
	/**
	 * ログの結果を文字列で返す
	 * @return
	 */
	public String getLog() {
		return log;
	}		
}
