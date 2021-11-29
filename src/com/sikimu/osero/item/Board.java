package com.sikimu.osero.item;

import java.util.ArrayList;
import java.util.List;

import com.sikimu.osero.Drawer;
import com.sikimu.osero.item.piece.Piece;
import com.sikimu.osero.item.piece.Piece.COLOR;

/**
 * オセロのボード
 * @author sikimu
 *
 */
public class Board {
	
	/**
	 * 各方向の移動量
	 */
	private final static int DIRECTION[][] = {
		{0, -1},			
		{0,  1},	
		{1, -1},				
		{1,  0},	
		{1,  1},	
		{-1, -1},		
		{-1,  0},
		{-1,  1},
	};
	
	/**
	 * 配置情報[y][x]
	 */
	private Piece pieceArray[][] = new Piece[8][8];
	
	/** 配置した駒 */
	private List<Piece> pieceList = new ArrayList<Piece>();
	
	/**
	 * コンストラクタ
	 */
	public Board() {
		//駒の初期配置
		setPiece(COLOR.BLACK, new BoardPos(3,3));
		setPiece(COLOR.BLACK, new BoardPos(4,4));
		setPiece(COLOR.WHITE, new BoardPos(3,4));
		setPiece(COLOR.WHITE, new BoardPos(4,3));
	}
	
	/**
	 * ボードにセットできるか
	 * @return
	 */
	public boolean isSetPiece(BoardPos pos) {
		return inBoard(pos.x, pos.y) && pieceArray[pos.y][pos.x] == null;
	}
	
	/**
	 * 指定座標がボード上の座標か？
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean inBoard(int x, int y) {
		if(x <  0 || getWidth() <= x) {
			return false;
		}
		if(y <  0 || getHeight() <= y) {
			return false;
		}
		return true;
	}
	
	/**
	 * ボードの横幅
	 * @return
	 */
	public int getWidth() {
		return pieceArray[0].length;
	}

	/**
	 * ボードの横幅
	 * @return
	 */
	public int getHeight() {
		return pieceArray.length;
	}		
	
	/**
	 * 駒の配置
	 * @param player 配置する色
	 * @param pos 配置座標
	 */
	public void setPiece(COLOR color, BoardPos pos) {		
		Piece piece = new Piece(color);
		pieceArray[pos.y][pos.x] = piece;
		pieceList.add(piece);
				
		reverse(color, pos);
	}
	
	/**
	 * 置ける場所の取得
	 * @param player　対象の色
	 * @return 置ける場所のリスト
	 */
	public List<BoardPos> getReverse(COLOR color){
		ArrayList<BoardPos> list = new ArrayList<BoardPos>();
		
		for(int y = 0;y < pieceArray.length;y++) {
			for(int x = 0;x < pieceArray[y].length;x++) {
				if(pieceArray[y][x] != null) {
					continue;
				}
				BoardPos pos = new BoardPos(x, y);
				if(countReverse(color, pos) > 0) {
					list.add(pos);
				}
			}
		}	
		
		return list;
	}
	
	/**
	 *　めくれる枚数(全方向)
	 * @param player 対象の色
	 * @param pos 対象の位置
	 * @return
	 */
	public int countReverse(COLOR color, BoardPos pos) {
		int cnt = 0;
		
		for(int[] direction : DIRECTION) {
			cnt += countReverse(color, pos, direction[0], direction[１]);
		}
		return cnt;
	}
	
	/**
	 * めくれる枚数
	 * @param player　対象の色
	 * @param pos 対象の位置
	 * @param moveX めくっていく方向x
	 * @param moveY めくっていく方向y
	 * @return めくれる枚数
	 */
	public int countReverse(COLOR color, BoardPos pos, int moveX, int moveY) {
		int cnt = 0;
		int x = pos.x + moveX;
		int y = pos.y + moveY;
		while(inBoard(x, y)) {
			if(pieceArray[y][x] == null) {
				return 0;
			}
			if(pieceArray[y][x].color == color) {
				return cnt;
			}
			cnt++;
			x = x + moveX;
			y = y + moveY;
		}		
		return 0;
	}

	/**
	 * 全方向をめくる
	 * @param color 対象の色
	 * @param pos　対象の位置
	 */
	public void reverse(COLOR color, BoardPos pos) {

		for(int[] direction : DIRECTION) {
			int cnt = countReverse(color, pos, direction[0], direction[１]);
			if(cnt > 0) {
				reverse(color, pos, direction[0], direction[1]);
			}
		}
	}	
	
	/**
	 * めくる
	 * @param player　対象の色
	 * @param pos 対象の位置
	 * @param moveX めくっていく方向x
	 * @param moveY めくっていく方向y
	 */
	private void reverse(COLOR color, BoardPos pos, int moveX, int moveY) {	
		int x = pos.x + moveX;
		int y = pos.y + moveY;
		while(inBoard(x, y)) {
			if(pieceArray[y][x].color == color) {
				return;
			}
			pieceArray[y][x].color = color;
			x = x + moveX;
			y = y + moveY;
		}				
	}
	
	/**
	 * 描画
	 */
	public void draw() {
		Drawer.draw(" 12345678");//縦の列の番号
		for(int i = 0;i < pieceArray.length;i++) {
			String str = "" + (i + 1);//横の列の番号
			for(Piece piece : pieceArray[i]) {
				if(piece == null) {
					str = str + "+";
				}
				else {
					str = str + piece.color.colorString;
				}
			}
			Drawer.draw(str);//1行づつ
		}		
	}

	/**
	 * 結果
	 * @return　結果の文字列
	 */
	public String createResult() {
		long b = pieceList.stream().filter(piece -> piece.color == COLOR.BLACK).count();
		long w = pieceList.stream().filter(piece -> piece.color == COLOR.WHITE).count();
		
		return "結果 B:" + b + " W:" + w;
	}
}
