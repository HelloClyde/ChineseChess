package whindow;

import java.awt.EventQueue;

import Audio.MP3;

public class ChineseChessApp {
	
	/**
	 * 运行程序入口
	 * 参数：命令符
	 * 返回：无
	 * 作者:孔胤栋
	 * 时间:2014-11-12
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ChineseChessMainFrame frame = new ChineseChessMainFrame();
					frame.setVisible(true);
					MP3 BgMusic = new MP3(ChineseChessMainFrame.class.getResource("/music/bgm.mp3").getPath().substring(1));
					BgMusic.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
