package whindow;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.JPanel;

import chessBoard.ChessBoarder;
import defultSet.DefultSet;

public class ChessBoarderCanvas extends JPanel{
	ChessBoarder MyChessBoarder;
	//Canvas的背景图片
	Image BgImage;
	//背景图片的左上角坐标
	int Mysx1;
	int Mysy1;
	//背景图片的右下角坐标
	int Mysx2;
	int Mysy2;
	//胜利图片
	char Winner = '无';
	
	/**
	 * 传递数据给Canvas绘制
	 * @param srcChess源棋盘
	 * @param srcImage
	 * @param sx1背景图片的左上角坐标x
	 * @param sy1背景图片的左上角坐标y
	 * @param sx2背景图片的右下角坐标x
	 * @param sy2背景图片的右下角坐标y
	 * @return成功返回0
	 * @author 孔胤栋
	 * 时间：20141120
	 */
	public int SendData(ChessBoarder srcChess,Image srcImage,int sx1,int sy1,int sx2,int sy2){
		MyChessBoarder = srcChess;
		BgImage = srcImage;
		Mysx1 = sx1;
		Mysy1 = sy1;
		Mysx2 = sx2;
		Mysy2 = sy2;
		return 0;
	}
	
	public void SendWinner(char a){
		this.Winner = a;
	}
	
	@Override
	/**
	 * 重载绘图函数paint
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//test
		System.out.println("Canvas paint done");
		
		//BufferedImage BImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
		//Graphics g = BImage.getGraphics();
		//画Canvas背景
		g.drawImage(BgImage, 0, 0, this.getWidth(), this.getHeight(), Mysx1, Mysy1, Mysx2, Mysy2,this);
		//画棋盘
		g.drawImage(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/chessboardafter.png")), 0, 0,
				Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/chessboardafter.png")).getWidth(null), 
				Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/chessboardafter.png")).getHeight(null), this);
		
		//画棋子
		int xx = DefultSet.ChessBoarderXX;
		int yy = DefultSet.ChessBoarderYY;
		int pp = DefultSet.ChessBoarderPP;
		for (int i = 0;i < 10;i ++){
			for (int j = 0;j < 9;j ++){
				//发现棋子就绘制
				if (ChineseChessMainFrame.MyBoarder.MyPieces[i][j] != null){
					g.drawImage(ChineseChessMainFrame.MyBoarder.MyPieces[i][j].Icon, 
							xx + j  * pp, yy + i * pp,
							ChineseChessMainFrame.MyBoarder.MyPieces[i][j].Icon.getWidth(null) , ChineseChessMainFrame.MyBoarder.MyPieces[i][j].Icon.getHeight(null), this);
				}
			}
		}
		//绘制2个框
		//System.out.println(ChineseChessMainFrame.MyBoarder.p1);
		if (ChineseChessMainFrame.MyBoarder.p1 != null){
			g.drawImage(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/kuang.png")), 
					xx +  ChineseChessMainFrame.MyBoarder.p1.x * pp, yy + ChineseChessMainFrame.MyBoarder.p1.y * pp,
					Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/kuang.png")).getWidth(null) , Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/kuang.png")).getHeight(null), this);
		}
		if (ChineseChessMainFrame.MyBoarder.p2 != null){
			g.drawImage(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/kuang.png")), 
					xx +  ChineseChessMainFrame.MyBoarder.p2.x * pp, yy + ChineseChessMainFrame.MyBoarder.p2.y * pp,
					Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/kuang.png")).getWidth(null) , Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/kuang.png")).getHeight(null), this);
		}
		//绘制胜利图片
		if (this.Winner == '红'){
			g.drawImage(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/red-win.png")), 50, 270,559, 132, this);
		}
		else if (this.Winner == '黑'){
			g.drawImage(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/black-win.png")), 50, 270,559, 132, this);
		}
		
		//gg.drawImage(BImage, 0, 0, null);
	}
}
