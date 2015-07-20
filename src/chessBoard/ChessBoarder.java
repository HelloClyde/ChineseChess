package chessBoard;

import java.awt.Point;

import whindow.ChineseChessMainFrame;

public class ChessBoarder {
	/*
	 * 9列10行的棋子数组
	 * null表示当前位置棋盘没有棋子
	 */
	public ChessPieces[][] MyPieces;
	
	//2个框框用来显示棋子走动路径,null表示不显示
	public Point p1;
	public Point p2;
	String [][] StrPos = {{"1","2","3","4","5","6","7","8","9"},
			{"九","八","七","六","五","四","三","二","一"}};
	
	/**
	 * 初始化棋盘
	 * @author 孔胤栋
	 * 时间：20141113
	 */
	public ChessBoarder(){
		//设2个框的初值
		p1 = null;
		p2 = null;
		//对棋子数组进行初始化
		MyPieces = new ChessPieces[10][9];
		for (int i = 0;i < 10;i ++){
			for (int j = 0;j < 9;j ++){
				MyPieces[i][j] = null;
			}
		}
		//设置默认棋子位置
		//设置黑车
		MyPieces[0][0] = new ChessPieces(1);
		//设置黑马
		MyPieces[0][1] = new ChessPieces(2);
		//设置黑相
		MyPieces[0][2] = new ChessPieces(5);
		//设置黑士
		MyPieces[0][3] = new ChessPieces(4);
		//设置黑将
		MyPieces[0][4] = new ChessPieces(0);
		//设置黑士
		MyPieces[0][5] = new ChessPieces(4);
		//设置黑相
		MyPieces[0][6] = new ChessPieces(5);
		//设置黑马
		MyPieces[0][7] = new ChessPieces(2);
		//设置黑车
		MyPieces[0][8] = new ChessPieces(1);
		//设置黑卒
		for (int i = 0;i < 9;i += 2){
			MyPieces[3][i] = new ChessPieces(6);
		}
		//设置黑炮
		MyPieces[2][1] = new ChessPieces(3);
		MyPieces[2][7] = new ChessPieces(3);
		
		//设置红车
		MyPieces[9][0] = new ChessPieces(8);
		//设置红马
		MyPieces[9][1] = new ChessPieces(9);
		//设置红象
		MyPieces[9][2] = new ChessPieces(13);
		//设置红士
		MyPieces[9][3] = new ChessPieces(11);
		//设置红帅
		MyPieces[9][4] = new ChessPieces(12);
		//设置红士
		MyPieces[9][5] = new ChessPieces(11);
		//设置红象
		MyPieces[9][6] = new ChessPieces(13);
		//设置红马
		MyPieces[9][7] = new ChessPieces(9);
		//设置红车
		MyPieces[9][8] = new ChessPieces(8);
		//设置红兵
		for (int i = 0;i < 9;i += 2){
			MyPieces[6][i] = new ChessPieces(7);
		}
		//设置红炮
		MyPieces[7][1] = new ChessPieces(10);
		MyPieces[7][7] = new ChessPieces(10);
		
	}
	/**
	 * 棋子移动
	 * @param src 原位置
	 * @param des 目标位置
	 * @return 移动成功返回true，否则返回false
	 * @author 孔胤栋
	 * 时间：20141118
	 */
	public boolean PieceMove(Point src,Point des){
		//test
		/*
		MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
		MyPieces[src.y][src.x] = null;
		*/
		
		if (MyPieces[src.y][src.x] == null){
			//源位置没有棋子，移动失败
			return false;
		}
		else{
			//有棋子
			switch(MyPieces[src.y][src.x].name.charAt(1)){
			case '将':
				//判断是否在九宫内
				if (des.x >= 3 && des.x <= 5 && des.y >= 0 && des.y <= 2){
					if (Distance(src,des) == 1){
						//行走步数为1步
						//输出走子信息
						if (src.y == des.y){
							ChineseChessMainFrame.InfBoard.AddLog("将" + StrPos[0][src.x] + "平" + StrPos[0][des.x]);
						}
						else{
							ChineseChessMainFrame.InfBoard.AddLog("将" + StrPos[0][src.x] + (des.y > src.y ? "进" : "退") + 1) ;
						}
						MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
						MyPieces[src.y][src.x] = null; 
						
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			case '帅':
				//判断是否在九宫内
				if (des.x >= 3 && des.x <= 5 && des.y >= 7 && des.y <= 9){
					if (Distance(src,des) == 1){
						//行走步数为1步
						//输出走子信息
						if (src.y == des.y){
							ChineseChessMainFrame.InfBoard.AddLog("帅" + StrPos[1][src.x] + "平" + StrPos[1][des.x]);
						}
						else{
							ChineseChessMainFrame.InfBoard.AddLog("帅" + StrPos[1][src.x] + (des.y < src.y ? "进" : "退") + 1) ;
						}
						MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
						MyPieces[src.y][src.x] = null; 
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			case '士':
				//判断是否是红方
				if (MyPieces[src.y][src.x].name.charAt(0) == '红'){
					//判断是否在九宫内
					if (des.x >= 3 && des.x <= 5 && des.y >= 7 && des.y <= 9){
						//判断是否是斜着走
						if (Distance(src,des) > 1.4 && Distance(src,des) < 1.5){
							//输出走子信息
							ChineseChessMainFrame.InfBoard.AddLog("士" + StrPos[1][src.x] + (des.y < src.y ? "进" : "退") +StrPos[1][des.x] ) ;
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
						else{
							return false;
						}
					}
					else{
						return false;
					}
				}
				else{
					//判断是否在九宫内
					if (des.x >= 3 && des.x <= 5 && des.y >= 0 && des.y <= 2){
						//判断是否是斜着走
						if (Distance(src,des) > 1.4 && Distance(src,des) < 1.5){
							//输出走子信息
							ChineseChessMainFrame.InfBoard.AddLog("士" + StrPos[1][src.x] + (des.y > src.y ? "进" : "退") +StrPos[1][des.x] ) ;
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
						else{
							return false;
						}
					}
				}
			case '象':
				//判断是否没过河
				if (des.y <= 4 ){
					//判断是否斜走2格
					if (Distance(src,des) > 2.8 && Distance(src,des) < 2.9){
						//是否未压象脚
						if (MyPieces[(des.y + src.y)/2][(des.x + src.x)/2] == null){
							//输出走子信息
							ChineseChessMainFrame.InfBoard.AddLog("象" + StrPos[0][src.x] + (des.y > src.y ? "进" : "退") +StrPos[0][des.x] ) ;
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
						else{
							return false;
						}
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			case '相':
				//判断是否没过河
				if (des.y >= 5 ){
					//判断是否斜走2格
					if (Distance(src,des) > 2.8 && Distance(src,des) < 2.9){
						//是否未压象脚
						if (MyPieces[(des.y + src.y)/2][(des.x + src.x)/2] == null){
							//输出走子信息
							ChineseChessMainFrame.InfBoard.AddLog("相" + StrPos[1][src.x] + (des.y < src.y ? "进" : "退") +StrPos[1][des.x] ) ;
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			case '马':
				//判断是否走2根号5格
				if (Distance(src,des) > 2.2 && Distance(src,des) < 2.3){
					//判断是否压马脚
					if (Math.abs(src.x - des.x) == 1){
						if (MyPieces[(src.y+des.y)/2][src.x] == null){
							if (MyPieces[src.y][src.x].name.charAt(0) == '黑' ){
								//输出走子信息
								ChineseChessMainFrame.InfBoard.AddLog("马" + StrPos[0][src.x] + (des.y > src.y ? "进" : "退") +StrPos[0][des.x] ) ;
							}
							else{
								//输出走子信息
								ChineseChessMainFrame.InfBoard.AddLog("马" + StrPos[1][src.x] + (des.y < src.y ? "进" : "退") +StrPos[1][des.x] ) ;
							}
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
						else{
							return false;
						}
					}
					else{
						if (MyPieces[src.y][(src.x+des.x)/2] == null){
							if (MyPieces[src.y][src.x].name.charAt(0) == '黑' ){
								//输出走子信息
								ChineseChessMainFrame.InfBoard.AddLog("马" + StrPos[0][src.x] + (des.y > src.y ? "进" : "退") +StrPos[0][des.x] ) ;
							}
							else{
								//输出走子信息
								ChineseChessMainFrame.InfBoard.AddLog("马" + StrPos[1][src.x] + (des.y < src.y ? "进" : "退") +StrPos[1][des.x] ) ;
							}
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
						else{
							return false;
						}
					}
				}
				else{
					return false;
				}
			case '车':
				if (src.y == des.y || src.x == des.x){
					//直线上没有棋子挡住
					if (IsBlock(src,des) == 0){
						if (MyPieces[src.y][src.x].name.charAt(0) == '红' ){
							if (src.y == des.y){
								ChineseChessMainFrame.InfBoard.AddLog("车" + StrPos[1][src.x] + "平" + StrPos[1][des.x]);
							}
							else{
								ChineseChessMainFrame.InfBoard.AddLog("车" + StrPos[1][src.x] + (des.y < src.y ? "进" : "退") + Math.abs(des.y - src.y)) ;
							}
						}
						else{
							if (src.y == des.y){
								ChineseChessMainFrame.InfBoard.AddLog("车" + StrPos[0][src.x] + "平" + StrPos[0][des.x]);
							}
							else{
								ChineseChessMainFrame.InfBoard.AddLog("车" + StrPos[0][src.x] + (des.y > src.y ? "进" : "退") + Math.abs(des.y - src.y)) ;
							}
						}
						
						MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
						MyPieces[src.y][src.x] = null; 
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			case '炮':
				if (src.y == des.y || src.x == des.x){
					//直线上没有棋子挡住
					if (IsBlock(src,des) == 0){
						if (MyPieces[src.y][src.x].name.charAt(0) == '红' ){
							if (src.y == des.y){
								ChineseChessMainFrame.InfBoard.AddLog("炮" + StrPos[1][src.x] + "平" + StrPos[1][des.x]);
							}
							else{
								ChineseChessMainFrame.InfBoard.AddLog("炮" + StrPos[1][src.x] + (des.y < src.y ? "进" : "退") + Math.abs(des.y - src.y)) ;
							}
						}
						else{
							if (src.y == des.y){
								ChineseChessMainFrame.InfBoard.AddLog("炮" + StrPos[0][src.x] + "平" + StrPos[0][des.x]);
							}
							else{
								ChineseChessMainFrame.InfBoard.AddLog("炮" + StrPos[0][src.x] + (des.y > src.y ? "进" : "退") + Math.abs(des.y - src.y)) ;
							}
						}
						MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
						MyPieces[src.y][src.x] = null; 
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			case '卒':
				//勇往直前
				if (des.y >= src.y){
					//过河
					if (des.y >= 5){
						if (Distance(src,des) == 1){
							if (src.y == des.y){
								ChineseChessMainFrame.InfBoard.AddLog("卒" + StrPos[0][src.x] + "平" + StrPos[0][des.x]);
							}
							else{
								ChineseChessMainFrame.InfBoard.AddLog("卒" + StrPos[0][src.x] +"进" + 1) ;
							}
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
						else{
							return false;
						}
					}
					else{
						if (des.x == src.x && Math.abs(des.y - src.y) == 1){
							ChineseChessMainFrame.InfBoard.AddLog("卒" + StrPos[0][src.x] + "进" + 1) ;
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
						else{
							return false;
						}
					}
				}
				else{
					return false;
				}
			case '兵':
				//勇往直前
				if (des.y <= src.y){
					//过河
					if (des.y <= 4){
						if (Distance(src,des) == 1){
							if (src.y == des.y){
								ChineseChessMainFrame.InfBoard.AddLog("兵" + StrPos[1][src.x] + "平" + StrPos[1][des.x]);
							}
							else{
								ChineseChessMainFrame.InfBoard.AddLog("兵" + StrPos[1][src.x] +"进" + 1) ;
							}
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
						else{
							return false;
						}
					}
					else{
						if (des.x == src.x && Math.abs(des.y - src.y) == 1){
							ChineseChessMainFrame.InfBoard.AddLog("兵" + StrPos[1][src.x] + "进" + 1) ;
							MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
							MyPieces[src.y][src.x] = null; 
							return true;
						}
						else{
							return false;
						}
					}
				}
				else{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * 棋子吃子
	 * @param src 原位置
	 * @param des 目标位置
	 * @return 移动成功返回true，否则返回false
	 * @author 孔胤栋
	 * 时间：20141118
	 */
	public boolean PieceEat(Point src,Point des){
		if (MyPieces[src.y][src.x] == null){
			//源位置没有棋子，吃子失败
			return false;
		}
		else{
			//有棋子
			switch(MyPieces[src.y][src.x].name.charAt(1)){
			//炮特殊处理
			case '炮':
				if (src.y == des.y || src.x == des.x){
					//直线上只有一个棋子挡住
					if (IsBlock(src,des) == 1){
						if (MyPieces[src.y][src.x].name.charAt(0) == '红' ){
							if (src.y == des.y){
								ChineseChessMainFrame.InfBoard.AddLog("炮" + StrPos[1][src.x] + "平" + StrPos[1][des.x]);
							}
							else{
								ChineseChessMainFrame.InfBoard.AddLog("炮" + StrPos[1][src.x] + (des.y < src.y ? "进" : "退") + Math.abs(des.y - src.y)) ;
							}
						}
						else{
							if (src.y == des.y){
								ChineseChessMainFrame.InfBoard.AddLog("炮" + StrPos[0][src.x] + "平" + StrPos[0][des.x]);
							}
							else{
								ChineseChessMainFrame.InfBoard.AddLog("炮" + StrPos[0][src.x] + (des.y > src.y ? "进" : "退") + Math.abs(des.y - src.y)) ;
							}
						}
						MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
						MyPieces[src.y][src.x] = null; 
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			default:
				 return PieceMove(src,des);
			}
		}
		//MyPieces[des.y][des.x] = MyPieces[src.y][src.x];
		//MyPieces[src.y][src.x] = null;
	}
	
	/**
	 * 
	 * @param a点a
	 * @param b点b
	 * @return ab间距离
	 * @author 孔胤栋
	 */
	public float Distance(Point a,Point b){
		System.out.println((float)Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)));
		return (float)Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
	}
	
	/**
	 * 
	 * @param a点a
	 * @param b点b
	 * @return ab间隔了多少棋子
	 * @author 孔胤栋
	 */
	public int IsBlock(Point a,Point b){
		int n = 0;
		if (a.x == b.x){
			int min,max;
			if (a.y > b.y){
				min = b.y;
				max = a.y;
			}
			else{
				min = a.y;
				max = b.y;
			}
			for (int i = min + 1;i < max;i ++){
				if (MyPieces[i][a.x] != null){
					n ++;
				}
			}
		}
		else{
			int min,max;
			if (a.x > b.x){
				min = b.x;
				max = a.x;
			}
			else{
				min = a.x;
				max = b.x;
			}
			for (int i = min + 1;i < max;i ++){
				if (MyPieces[a.y][i] != null){
					n ++;
				}
			}
		}
		return n;
	}
	/**
	 * 得到胜利方
	 * @return 胜利方，红，黑，无
	 * @author 孔胤栋
	 */
	public char Winner(){
		//判断将是否存在
		Point pp1 = null;
		boolean ExJ = false;
		for (int i = 0;i <= 8;i ++){
			for (int j = 0;j <=9;j ++){
				if (MyPieces[j][i] != null){
					if (MyPieces[j][i].name.charAt(1) == '将'){
						pp1 = new Point(i,j);
						ExJ = true;
					}
				}
			}
		}
		if (!ExJ){
			ChineseChessMainFrame.DoPlayer = '无';
			return '红';
		}
		//判断帅是否存在
		Point pp2 = null;
		boolean ExS = false;
		for (int i = 0;i <= 8;i ++){
			for (int j = 0;j <=9;j ++){
				if (MyPieces[j][i] != null){
					if (MyPieces[j][i].name.charAt(1) == '帅'){
						pp2 = new Point(i,j);
						ExS = true;
					}
				}
			}
		}
		if (!ExS){
			ChineseChessMainFrame.DoPlayer = '无';
			return '黑';
		}
		//都存在
		//判断是否对将
		if (pp1.x == pp2.x && IsBlock(pp1,pp2) == 0){
			char TempC;
			TempC = ChineseChessMainFrame.DoPlayer;
			if (TempC == '红'){
				TempC = '黑';
			}
			else if (TempC == '黑'){
				TempC = '红';
			}
			ChineseChessMainFrame.DoPlayer = '无';
			System.out.println(TempC);
			return TempC;
		}
		
		return '无';
	}
}
