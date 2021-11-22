package com.sikimu.osero.item;

import java.util.ArrayList;

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
		setPiece(playerList.get(0), 3, 3);
		setPiece(playerList.get(0), 4, 4);
		setPiece(playerList.get(1), 3, 4);
		setPiece(playerList.get(1), 4, 3);
	}
	
	/**
	 * 駒の配置
	 * @param player 配置するプレイヤー
	 * @param x 配置x座標
	 * @param y 配置y座標
	 */
	private void setPiece(Player player, int x, int y) {
		
		Piece piece = new Piece(player.getColor());
		pieceList[y][x] = piece;
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
