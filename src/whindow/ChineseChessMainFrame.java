package whindow;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import whindow.LabelEvent.ChessPieceClick;
import chessBoard.ChessBoarder;
import defultSet.DefultSet;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;

public class ChineseChessMainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel Pane1;
	private JPanel Pane2;
	private JPanel Pane3;
	private JPanel Pane4;
	
	static public InformationBoard InfBoard;
	
	/**
	 * 游戏模式
	 * 0：双人对决
	 * 1：人机对决
	 * 2：棋盘演示
	 * 3：退出游戏
	 */
	static public int MenuMode = 0;
	/**
	 * 执子方
	 * 红:红方
	 * 黑:黑方
	 * 无：无，不能下子
	 */
	static public char DoPlayer = '红';
	//棋盘数据
	static public ChessBoarder MyBoarder;
	
	ChessBoarderCanvas MyCanvas;

	/**
	 * 创建主窗口，构造函数，搭建界面
	 * 参数：无
	 * 返回：
	 * 作者：孔胤栋
	 * 时间：2014-11-12
	 */
	public ChineseChessMainFrame() {
		
		//数据初始化
		DataInit();
		
		//设置图标
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/black-jiang.png")));
		//设置标题
		this.setTitle("中国象棋");
		//设置窗口大小
		this.setBounds(0, 0, 1366, 768);
		//设置窗口不可改变大小
		this.setResizable(false);
		//设置默认关闭
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置窗口居中
		this.setLocationRelativeTo(null);
		
		//设置ContentPane属性
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//不使用布局
		contentPane.setLayout(null);
		//设置ContentPane为透明
		contentPane.setOpaque(false);
		this.setContentPane(contentPane);
		
		//设置ContentPane上信息
		
		//添加背景图片
		JLabel BackGround = new JLabel("");
		BackGround.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/background.png"))));
		BackGround.setBounds(0, 0, 1366, 768);
		//添加背景图片的关键语句
		this.getLayeredPane().add(BackGround, new Integer(Integer.MIN_VALUE)); 
		
		//添加作者信息
		JLabel AuthorInf = new JLabel("");
		AuthorInf.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/information.png"))));
		//设置作者信息位置
		AuthorInf.setBounds(30, 500,
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/information.png")).getIconWidth(), 
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/information.png")).getIconHeight());
		contentPane.add(AuthorInf);
		
		//添加4个按钮
		JLabel Menu1 = new JLabel("");
		Menu1.setOpaque(false);
		JLabel Menu2 = new JLabel("");
		Menu2.setOpaque(false);
		JLabel Menu3 = new JLabel("");
		Menu3.setOpaque(false);
		JLabel Menu4 = new JLabel("");
		Menu4.setOpaque(false);
		
		//双人对决按钮属性设置
		//设置初始图片
		Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
		//添加鼠标事件
		Menu1.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				MenuMode = 0;
				Pane1.setVisible(true);
				Pane2.setVisible(false);
				Pane3.setVisible(false);
				Pane4.setVisible(false);
			}
			public void mouseEntered(MouseEvent arg0){
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
			}
			public void mouseExited(MouseEvent arg0){
				//全部初始化为图片0
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
				//设置MenuMode图片
				if (MenuMode == 0){
					Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				}
				else if (MenuMode == 1){
					Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				}
				else if (MenuMode == 2){
					Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				}
				else if (MenuMode == 3){
					Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
				}
			}
		});
		//设置按钮位置和大小
		Menu1.setBounds(60, 60,
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png")).getIconWidth(), 
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png")).getIconHeight());
		//添加按钮至ContentPane
		contentPane.add(Menu1);
	
		
		//人机对决按钮属性设置
		//设置初始图片
		Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
		//添加鼠标事件
		Menu2.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				MenuMode = 1;
				Pane1.setVisible(false);
				Pane2.setVisible(true);
				Pane3.setVisible(false);
				Pane4.setVisible(false);
			}
			public void mouseEntered(MouseEvent arg0){
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
			}
			public void mouseExited(MouseEvent arg0){
				//全部初始化为图片0
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
				//设置MenuMode图片
				if (MenuMode == 0){
					Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				}
				else if (MenuMode == 1){
					Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				}
				else if (MenuMode == 2){
					Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				}
				else if (MenuMode == 3){
					Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
				}
			}
		});
		//设置按钮位置和大小
		Menu2.setBounds(60, 140,
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png")).getIconWidth(),
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png")).getIconHeight());
		//添加按钮至ContentPane
		contentPane.add(Menu2);
		
		//棋盘演示按钮属性设置
		//设置初始图片
		Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
		//添加鼠标事件
		Menu3.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				MenuMode = 2;
				Pane1.setVisible(false);
				Pane2.setVisible(false);
				Pane3.setVisible(true);
				Pane4.setVisible(false);
			}
			public void mouseEntered(MouseEvent arg0){
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
			}
			public void mouseExited(MouseEvent arg0){
				//全部初始化为图片0
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
				//设置MenuMode图片
				if (MenuMode == 0){
					Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				}
				else if (MenuMode == 1){
					Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				}
				else if (MenuMode == 2){
					Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				}
				else if (MenuMode == 3){
					Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
				}
			}
		});
		
		//设置按钮位置和大小
		Menu3.setBounds(60, 220,
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png")).getIconWidth(), 
				new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png")).getIconHeight());
		//添加按钮至ContentPane
		contentPane.add(Menu3);
		
		//退出游戏按钮属性设置
		//设置初始图片
		Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
		//添加鼠标事件
		Menu4.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				MenuMode = 3;
				Pane1.setVisible(false);
				Pane2.setVisible(false);
				Pane3.setVisible(false);
				Pane4.setVisible(true);
				//退出游戏
				dispose();
				System.exit(0);
			}
			public void mouseEntered(MouseEvent arg0){
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
			}
			public void mouseExited(MouseEvent arg0){
				//全部初始化为图片0
				Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_0.png"))));
				Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_0.png"))));
				Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_0.png"))));
				Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png"))));
				//设置MenuMode图片
				if (MenuMode == 0){
					Menu1.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu1_1.png"))));
				}
				else if (MenuMode == 1){
					Menu2.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu2_1.png"))));
				}
				else if (MenuMode == 2){
					Menu3.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu3_1.png"))));
				}
				else if (MenuMode == 3){
					Menu4.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_1.png"))));
				}
			}
		});
		
		//设置按钮位置和大小
		//Menu4.setBounds(60, 300,new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png")).getIconWidth(), new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png")).getIconHeight());
		Menu4.setBounds(60, 140,new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png")).getIconWidth(), new ImageIcon(ChineseChessMainFrame.class.getResource("/imageLibary/Menu4_0.png")).getIconHeight());
		//添加按钮至ContentPane
		contentPane.add(Menu4);
		
		//暂时屏蔽人机和演示模式
		Menu2.setVisible(false);
		Menu3.setVisible(false);
		
		//初始化4个JPanel
		Pane1 = new JPanel();
		Pane2 = new JPanel();
		Pane3 = new JPanel();
		Pane4 = new JPanel();
		
		//设置4个JPanel的位置和共同属性
		Pane1.setBounds(0, 0, 1366, 768);
		Pane1.setOpaque(false);
		Pane1.setVisible(true);
		Pane1.setLayout(null);
		Pane2.setBounds(0, 0, 1366, 768);
		Pane2.setOpaque(false);
		Pane2.setVisible(false);
		Pane2.setLayout(null);
		Pane3.setBounds(0, 0, 1366, 768);
		Pane3.setOpaque(false);
		Pane3.setVisible(false);
		Pane3.setLayout(null);
		Pane4.setBounds(0, 0, 1366, 768);
		Pane4.setOpaque(false);
		Pane4.setVisible(false);
		Pane4.setLayout(null);
		
		//把4个Pane添加进ContentPanel
		contentPane.add(Pane1);
		contentPane.add(Pane2);
		contentPane.add(Pane3);
		contentPane.add(Pane4);
		
		//对Pane1添加Canvas来绘制棋盘
		MyCanvas = new ChessBoarderCanvas();
		//设置Canvas位置和大小
		MyCanvas.setBounds(DefultSet.CanvasPosX, DefultSet.CanvasPosY, 661, 728);
		//为Canvas传递数据
		MyCanvas.SendData(this.MyBoarder, Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/background.png")), DefultSet.CanvasPosX, DefultSet.CanvasPosY, DefultSet.CanvasPosX+661, DefultSet.CanvasPosY+728);
		MyCanvas.repaint();
		MyCanvas.addMouseListener(new ChessPieceClick());
		Pane1.add(MyCanvas);
		
		//对Pane1添加信息栏
		InfBoard = new InformationBoard();
		InfBoard.setBounds(1011, 50, 394, 481);
		Pane1.add(InfBoard);
		
		InfBoard.AddLog("红方执子");
		
		//添加重新开始按钮
		DiyButton AllReset = new DiyButton("/imageLibary/ButtonAllReset_0.png","/imageLibary/ButtonAllReset_1.png");
		AllReset.setBounds(1011, 600, 326, 115);
		AllReset.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0){
				DataInit();
				InfBoard.Clear();
				InfBoard.AddLog("红方执子");
				MyCanvas.SendWinner('无');
				MyCanvas.paintImmediately(0, 0, MyCanvas.getWidth(), MyCanvas.getHeight());
			}
		});
		Pane1.add(AllReset);
		
		//添加时间标签
		JLabel TimerLabel = new JLabel();
		TimerLabel.setBounds(1030, 570, 100, 50);
		TimerLabel.setFont(new Font("华文行楷",Font.CENTER_BASELINE,28));
		Pane1.add(TimerLabel);
		TimerThread MyTimerThread = new TimerThread(TimerLabel);
		MyTimerThread.start();
	}
	
	/**基本数据初始化
	 * @author 孔胤栋
	 * 时间：20141113
	 */
	public void DataInit(){
		MenuMode = 0;
		DoPlayer = '红';
		MyBoarder = new ChessBoarder();
	}

}
