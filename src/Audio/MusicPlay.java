package Audio;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import whindow.ChineseChessMainFrame;

 
public class MusicPlay {
	
	static public void main(String[] args){
		try{
			// Open an audio input stream.
		    URL url = ChineseChessMainFrame.class.getClassLoader().getResource("/music/boom1.wav");
		    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
		    // Get a sound clip resource.
		    Clip clip = AudioSystem.getClip();
		    // Open audio clip and load samples from the audio input stream.
		    clip.open();
		    clip.start();
		} catch (UnsupportedAudioFileException e) {
		    e.printStackTrace();
		 } catch (IOException e1) {
		    e1.printStackTrace();
		 } catch (LineUnavailableException e2) {
		    e2.printStackTrace();
		 }
	}
	
}
