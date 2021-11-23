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
		return inBoard(pos.x, pos.y);
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
				
		BoardPos p = search(player, pos.x, pos.y , 1, 0);
	}
	
	private BoardPos search(Player player, int x, int y, int moveX, int moveY) {				
		x = x + moveX;
		y = y + moveY;
		if(inBoard(x, y) == false || pieceList[y][x] == null) {
			return null;
		}
		if(pieceList[y][x].color == player.getColor()) {
			return new BoardPos(x, y);
		}
		return search(player, x, y, moveX, moveY);
	}
	
	public void reverse(List<Piece> list, Player player, int moveX, int moveY) {
		
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
