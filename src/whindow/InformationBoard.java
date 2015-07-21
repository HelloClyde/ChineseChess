package whindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 用于显示信息的类，显示log中的最后10行
 * @author 蒲思瀚
 * @time 20141127
 *
 */
public class InformationBoard extends JPanel{
	//记录数据
	StringBuffer log = new StringBuffer();
	//用于储存最后10行
	String[] logS = new String[10];
	//Add方法的插入位置
	int Address = 0;
	
	public InformationBoard(){
		super();
		//设置界面透明
		this.setOpaque(false);
		//初始化显示的10行
		for (int i = 0;i < 10; i ++){
			logS[i] = new String("");
		}
		//设置插入位置为第0行
		Address = 0;
	}
	public void paintComponent(Graphics g){
		//定义一个空的图片流
		BufferedImage BImage = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_ARGB);
		//获取图片流的Graphics
		Graphics2D g2d = BImage.createGraphics();
		//绘制背景图片
		g2d.drawImage(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource("/imageLibary/InfBoard.png")), 0, 0,this);
		//设置字体颜色
		g2d.setColor(Color.white);
		//设置字体
		g2d.setFont(new Font("华文行楷",Font.CENTER_BASELINE,28));
		//开启字体抗锯齿
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//绘制字体，内容为需要显示的10行文字
		for (int i = 0;i < 10;i ++){
			g2d.drawString(logS[i], 100, 60 + i * 40);
		}
		//刷新图片流至g
		g.drawImage(BImage, 0, 0, null);
	}
	
	public String AddLog(String s){
		//记录数据后添加一行
		log.append(s + "\n");
		//原记录小于10行的处理
		if (Address < 10){
			logS[Address] = s;
			Address ++;
		}
		//原记录大于等于10行的处理
		else{
			for (int i = 0;i < 9;i ++){
				logS[i] = logS[i + 1];
			}
			logS[9] = s;
		}
		//刷新界面
		this.paintImmediately(0, 0, this.getWidth(), this.getHeight());
		return new String(log);
	}
	
	public void Clear(){
		//清空log
		this.log = new StringBuffer();
		//清空显示的10行
		for (int i = 0;i < 10; i ++){
			logS[i] = new String("");
		}
		//清空插入位置
		Address = 0;
	}
}
