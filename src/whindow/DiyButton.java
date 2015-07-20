package whindow;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DiyButton extends JLabel {
	//2张图片用于未选中和选中
	ImageIcon p0;
	ImageIcon p1;
	public DiyButton(String pic0,String pic1){
		//给2图片赋值
		p0 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource(pic0)));
		p1 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(ChineseChessMainFrame.class.getResource(pic1)));
		//初始图片为为选中图片
		this.setIcon(p0);
		//添加鼠标监听事件
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent arg0){
				//设置图片为选中状态
				((DiyButton)arg0.getSource()).setIcon(p1);
			}
			public void mouseExited(MouseEvent arg0){
				//设置图片为未选中状态
				((DiyButton)arg0.getSource()).setIcon(p0);
			}
		});
	}
}
