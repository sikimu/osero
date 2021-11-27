package com.sikimu.osero.item;

import java.util.ArrayList;
import java.util.List;

import com.sikimu.osero.Drawer;
import com.sikimu.osero.item.piece.Piece;
import com.sikimu.osero.player.Player;

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
		{1, -1},				
		{1,  1},
	};
	
	/**
	 * 配置情報[y][x]
	 */
	private Piece pieceList[][] = new Piece[8][8];
	
	/**
	 * コンストラクタ
	 * @param playerList
	 */
	public Board(ArrayList<Player> playerList) {
		//駒の初期配置
		setPiece(playerList.get(0), new BoardPos(3,3));
		setPiece(playerList.get(0), new BoardPos(4,4));
		setPiece(playerList.get(1), new BoardPos(3,4));
		setPiece(playerList.get(1), new BoardPos(4,3));
	}
	
	/**
	 * ボードにセットできるか
	 * @return
	 */
	public boolean isSetPiece(Player player, BoardPos pos) {
		return inBoard(pos.x, pos.y) && pieceList[pos.y][pos.x] == null;
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
		return pieceList[0].length;
	}

	/**
	 * ボードの横幅
	 * @return
	 */
	public int getHeight() {
		return pieceList.length;
	}		
	
	/**
	 * 駒の配置
	 * @param player 配置するプレイヤー
	 * @param pos 配置座標
	 */
	public void setPiece(Player player, BoardPos pos) {		
		Piece piece = new Piece(player.getColor());
		pieceList[pos.y][pos.x] = piece;
				
		reverse(player, pos);
	}
	
	/**
	 *　めくれる枚数(全方向)
	 * @param player //対象のプレイヤー
	 * @param pos 対象の位置
	 * @return
	 */
	public int countReverse(Player player, BoardPos pos) {
		int cnt = 0;
		
		for(int[] direction : DIRECTION) {
			cnt += countReverse(player, pos, direction[0], direction[１]);
		}
		return cnt;
	}
	
	/**
	 * めくれる枚数
	 * @param player　対象のプレイヤー
	 * @param pos 対象の位置
	 * @param moveX めくっていく方向x
	 * @param moveY めくっていく方向y
	 * @return めくれる枚数
	 */
	public int countReverse(Player player, BoardPos pos, int moveX, int moveY) {
		int cnt = 0;
		int x = pos.x + moveX;
		int y = pos.y + moveY;
		while(inBoard(x, y)) {
			if(pieceList[y][x] == null) {
				return 0;
			}
			if(pieceList[y][x].color == player.getColor()) {
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
	 * @param player 対象のプレイヤー
	 * @param pos　対象の位置
	 */
	public void reverse(Player player, BoardPos pos) {

		for(int[] direction : DIRECTION) {
			int cnt = countReverse(player, pos, direction[0], direction[１]);
			if(cnt > 0) {
				reverse(player, pos, direction[0], direction[1]);
			}
		}
	}	
	
	/**
	 * めくる
	 * @param player　対象のプレイヤー
	 * @param pos 対象の位置
	 * @param moveX めくっていく方向x
	 * @param moveY めくっていく方向y
	 */
	private void reverse(Player player, BoardPos pos, int moveX, int moveY) {	
		int x = pos.x + moveX;
		int y = pos.y + moveY;
		while(inBoard(x, y)) {
			if(pieceList[y][x].color == player.getColor()) {
				return;
			}
			pieceList[y][x].color = player.getColor();
			x = x + moveX;
			y = y + moveY;
		}				
	}
	
	/**
	 * 描画
	 */
	public void draw() {
		Drawer.draw(" 12345678");//縦の列の番号
		for(int i = 0;i < pieceList.length;i++) {
			String str = "" + (i + 1);//横の列の番号
			for(Piece piece : pieceList[i]) {
				if(piece == null) {
					str = str + "+";
				}
				else {
					str = str + piece.toString();
				}
			}
			Drawer.draw(str);//1行づつ
		}		
	}
}
