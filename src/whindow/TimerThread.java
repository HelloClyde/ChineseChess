package whindow;

import java.awt.Color;

import javax.swing.JLabel;

import whindow.LabelEvent.ChessPieceClick;

public class TimerThread extends Thread{
	JLabel desLabel;
	
	public TimerThread(JLabel src){
		desLabel = src;
	}
	
	public void run(){
		char initPlayer = ChineseChessMainFrame.DoPlayer;
		long oldTime = System.currentTimeMillis();
		desLabel.setText("30");
		while (!Thread.interrupted()){
			if (ChineseChessMainFrame.DoPlayer != 'нч'){
				if (ChineseChessMainFrame.DoPlayer == initPlayer){
					if (System.currentTimeMillis() > oldTime + 30000){
						ChessPieceClick.SwitchDoPlayer();
						initPlayer = ChineseChessMainFrame.DoPlayer;
						oldTime = System.currentTimeMillis();
						desLabel.setText("30");
					}
				}
				else{
					initPlayer = ChineseChessMainFrame.DoPlayer;
					oldTime = System.currentTimeMillis();
					desLabel.setText("30");
				}
				if (30 - (System.currentTimeMillis() - oldTime) / 1000 <= 5){
					desLabel.setForeground(Color.red);
				}
				else{
					desLabel.setForeground(Color.black);
				}
				desLabel.setText(String.valueOf(30 - (System.currentTimeMillis() - oldTime) / 1000));
			}
			else{
				desLabel.setText("");
			}
		}
	}
}
