package whindow.LabelEvent;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Audio.MP3;
import defultSet.DefultSet;
import whindow.ChessBoarderCanvas;
import whindow.ChineseChessMainFrame;

/**
 * 
 * @author 唐伟峰
 *时间：20141121
 */
public class ChessPieceClick extends MouseAdapter {
	@Override
	public void mouseClicked(MouseEvent arg0){
		MP3 DoPieceSound = new MP3(ChineseChessMainFrame.class.getResource("/music/dopiece.wav").getPath().substring(1),false);
		MP3 WinSound = new MP3(ChineseChessMainFrame.class.getResource("/music/win.wav").getPath().substring(1),false);
		int x,y;
		x = arg0.getX();
		y = arg0.getY();
		x = (x - DefultSet.ChessBoarderXX) / DefultSet.ChessBoarderPP;
		y = (y - DefultSet.ChessBoarderYY) / DefultSet.ChessBoarderPP;
		System.out.println(x + "," +  y);
		if (x < 0 || x > 8 || y < 0 || y > 9){
			//点击在棋盘外，本次点击无效
			return;
		}
		else{
			if (ChineseChessMainFrame.MyBoarder.p1 == null){
			//无选中棋子
				System.out.println("无选中棋子");
				ChineseChessMainFrame.MyBoarder.p1 = new Point(x,y);
				//System.out.println(ChineseChessMainFrame.MyBoarder.p1);
			}
			else{
				//有选中棋子
				System.out.println("有选中棋子");
				if (ChineseChessMainFrame.MyBoarder.MyPieces[ChineseChessMainFrame.MyBoarder.p1.y][ChineseChessMainFrame.MyBoarder.p1.x] == null){
					//选中棋子为空
					System.out.println("选中棋子为空");
					ChineseChessMainFrame.MyBoarder.p1 = new Point(x,y);
				}
				else{
					//选中棋子不为空
					System.out.println("选中棋子不为空");
					if (ChineseChessMainFrame.MyBoarder.MyPieces[ChineseChessMainFrame.MyBoarder.p1.y][ChineseChessMainFrame.MyBoarder.p1.x].name.charAt(0) != ChineseChessMainFrame.DoPlayer){
						//选中的是非本方棋子
						System.out.println("选中的是非本方棋子");
						ChineseChessMainFrame.MyBoarder.p1 = new Point(x,y);
					}
					else{
						//选中的是本方棋子
						System.out.println("选中的是本方棋子");
						if (ChineseChessMainFrame.MyBoarder.MyPieces[y][x] == null){
							//第二次选中为空
							System.out.println("第二次选中为空");
							ChineseChessMainFrame.MyBoarder.p2 = new Point(x,y);
							if (ChineseChessMainFrame.MyBoarder.PieceMove(ChineseChessMainFrame.MyBoarder.p1, ChineseChessMainFrame.MyBoarder.p2) == true){
								//棋子可以移动
								DoPieceSound.play();
								System.out.println("棋子可以移动");
								char Winner = ChineseChessMainFrame.MyBoarder.Winner();
								if (Winner == '红'){
									WinSound.play();
									((ChessBoarderCanvas)arg0.getSource()).SendWinner('红');
									ChineseChessMainFrame.InfBoard.AddLog("红方获得胜利!");
								}
								else if (Winner == '黑'){
									WinSound.play();
									((ChessBoarderCanvas)arg0.getSource()).SendWinner('黑');
									ChineseChessMainFrame.InfBoard.AddLog("黑方获得胜利!");
								}
								else{
									ChineseChessMainFrame.MyBoarder.p1 = null;
									ChineseChessMainFrame.MyBoarder.p2 = null;
									SwitchDoPlayer();
								}
								
							}
							else{
								//棋子不能移动
								System.out.println("棋子不能移动");
								ChineseChessMainFrame.MyBoarder.p1 = new Point(x,y);
								ChineseChessMainFrame.MyBoarder.p2 = null;
							}
						}
						else{
							//第二次选中非空
							System.out.println("第二次选中为空");
							if (ChineseChessMainFrame.MyBoarder.MyPieces[y][x].name.charAt(0) == ChineseChessMainFrame.DoPlayer){
								//第二次选中的还是本方棋子
								System.out.println("第二次选中的还是本方棋子");
								ChineseChessMainFrame.MyBoarder.p1 = new Point(x,y);
							}
							else{
								//第二次选中的是敌方棋子
								System.out.println("第二次选中的是敌方棋子");
								ChineseChessMainFrame.MyBoarder.p2 = new Point(x,y);
								if (ChineseChessMainFrame.MyBoarder.PieceEat(ChineseChessMainFrame.MyBoarder.p1, ChineseChessMainFrame.MyBoarder.p2) == true){
									//棋子可以吃
									DoPieceSound.play();
									System.out.println("棋子可以吃");
									char Winner = ChineseChessMainFrame.MyBoarder.Winner();
									if (Winner == '红'){
										WinSound.play();
										((ChessBoarderCanvas)arg0.getSource()).SendWinner('红');
										ChineseChessMainFrame.InfBoard.AddLog("红方获得胜利!");
									}
									else if (Winner == '黑'){
										WinSound.play();
										((ChessBoarderCanvas)arg0.getSource()).SendWinner('黑');
										ChineseChessMainFrame.InfBoard.AddLog("黑方获得胜利!");
									}
									else{
										ChineseChessMainFrame.MyBoarder.p1 = null;
										ChineseChessMainFrame.MyBoarder.p2 = null;
										SwitchDoPlayer();
									}
								}
								else{
									//棋子不能吃
									System.out.println("棋子不能吃");
									ChineseChessMainFrame.MyBoarder.p1 = new Point(x,y);
									ChineseChessMainFrame.MyBoarder.p2 = null;
								}
							}
						}
					}
				}
			}
		}
		((ChessBoarderCanvas)arg0.getSource()).repaint();
		((ChessBoarderCanvas)arg0.getSource()).paintImmediately(0, 0, ((ChessBoarderCanvas)arg0.getSource()).getWidth(), ((ChessBoarderCanvas)arg0.getSource()).getHeight());
		System.out.println("repaint done");
	}
	
	/**
	 * 交换棋子执行方
	 * @author 唐伟峰
	 * 时间：20141121
	 */
	static public void SwitchDoPlayer(){
		if (ChineseChessMainFrame.DoPlayer == '红'){
			ChineseChessMainFrame.DoPlayer = '黑';
			ChineseChessMainFrame.InfBoard.AddLog("黑方执子");
			System.out.println("轮到黑方");
		}
		else if (ChineseChessMainFrame.DoPlayer == '黑'){
			ChineseChessMainFrame.DoPlayer = '红';
			ChineseChessMainFrame.InfBoard.AddLog("红方执子");
			System.out.println("轮到红方");
		}
		else{
			//什么都不做
		}
	}
}
