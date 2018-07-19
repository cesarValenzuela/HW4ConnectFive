package cs3331;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * The code for this was inspired by many youtube turoials, the main video link was https://www.youtube.com/watch?v=QVrxiJyLTqU
 * the sound file is from https://www.freesfx.co.uk/rx2/mp3s/5/16961_1461335343.mp3
 * @author Andrea Fernanda Torres
 */
public class Sound{
    /**
     *
     */
    public static void playSound(){
        try{
            Clip audioClip=AudioSystem.getClip();
            audioClip.open(AudioSystem.getAudioInputStream(new File("src\\Sound\\click (1).wav")));
            audioClip.start();
            Thread.sleep(audioClip.getMicrosecondLength()/1000);
        }catch(Exception e){
            System.out.println("Sound Problem");
        }
    }


}