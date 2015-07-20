package chessBoard;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import whindow.ChineseChessMainFrame;

/**
 * 棋盘棋子类
 * @author 孔胤栋
 * 时间：20141113
 */
public class ChessPieces {
	public String name;
	/**
	 * 0：黑将
	 * 1：黑车
	 * 2：黑马
	 * 3：黑炮
	 * 4：黑士
	 * 5：黑相
	 * 6：黑卒
	 * 7：红兵
	 * 8：红车
	 * 9：红马
	 * 10：红炮
	 * 11：红士
	 * 12：红帅
	 * 13：红象
	 */
	public int id;
	public Image Icon;
	
	public ChessPieces(int Id){
		this.id = Id;
		String FileName = null;
		if (this.id == 0){
			this.name = new String("黑将");
			FileName = new String("black-jiang");
		}
		else if (this.id == 1){
			this.name = new String("黑车");
			FileName = new String("black-ju");
		}
		else if (this.id == 2){
			this.name = new String("黑马");
			FileName = new String("black-ma");
		}
		else if (this.id == 3){
			this.name = new String("黑炮");
			FileName = new String("black-pao");
		}
		else if (this.id == 4){
			this.name = new String("黑士");
			FileName = new String("black-shi");
		}
		else if (this.id == 5){
			this.name = new String("黑象");
			FileName = new String("black-xiang");
		}
		else if (this.id == 6){
			this.name = new String("黑卒");
			FileName = new String("black-zu");
		}
		else if (this.id == 7){
			this.name = new String("红兵");
			FileName = new String("red-bing");
		}
		else if (this.id == 8){
			this.name = new String("红车");
			FileName = new String("red-ju");
		}
		else if (this.id == 9){
			this.name = new String("红马");
			FileName = new String("red-ma");
		}
		else if (this.id == 10){
			this.name = new String("红炮");
			FileName = new String("red-pao");
		}
		else if (this.id == 11){
			this.name = new String("红士");
			FileName = new String("red-shi");
		}
		else if (this.id == 12){
			this.name = new String("红帅");
			FileName = new String("red-shuai");
		}
		else if (this.id == 13){
			this.name = new String("红相");
			FileName = new String("red-xiang");
		}
		
		//设置ImageIcon
		this.Icon = Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/" + FileName + ".png"));
	}
}
