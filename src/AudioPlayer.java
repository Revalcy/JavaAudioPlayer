import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    private Clip clip;
    private long clipTime;

    public AudioPlayer(File file)
            throws UnsupportedAudioFileException,
            IOException,
            LineUnavailableException {

        AudioInputStream stream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(stream);

        long durationSeconds = clip.getMicrosecondLength() / 1_000_000;
        System.out.println("Song duration: " + durationSeconds + " seconds");

        clip.addLineListener(event -> {
            if(event.getType() != LineEvent.Type.STOP) return;

            if(clip.getMicrosecondPosition() >= clip.getMicrosecondLength()){
                clipTime = 0;
                clip.setMicrosecondPosition(0);
            }
        });
    }

    public void play(){
        if(clip.isRunning()){
            System.out.println("Already playing...");
            return;
        }

        clip.setMicrosecondPosition(clipTime);
        clip.start();
        System.out.println("Playing...");
    }

    public void stop(){
        clipTime = clip.getMicrosecondPosition();
        clip.stop();
    }

    public void reset(){
        clip.stop();
        clipTime = 0;
        clip.setMicrosecondPosition(0);
    }

    public void close(){
        clip.close();
    }
}
