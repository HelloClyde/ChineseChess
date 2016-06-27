package Audio;
import javax.media.bean.playerbean.MediaPlayer;

public class MP3 {
    private String str;
    private MediaPlayer playMP3;
    
    public static void main(String[] args){
    	MP3 testMP3 = new MP3("d:/bgm.mp3");
    	testMP3.play();
    }
    
    public MP3(String str){
    	this(str,true);
    }
   
    public MP3(String str,boolean IsLoop){
        this.str = str;
        playMP3 = new MediaPlayer();
        playMP3.setMediaLocation("file:/"+str);
        playMP3.realize();
        playMP3.setPlaybackLoop(IsLoop);
    }
   
    public void play(){
        try{
	        playMP3.start();
	        System.out.println("¿ªÊ¼²¥·Å" + str);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
   
    public void stop(){
        playMP3.stop();
    }
   
    public String getSTR(){
        return str;
    }
   
    public void setSTR(String str){
        this.str = str;
    }
}
