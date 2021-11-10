import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {

    public static synchronized void playSound(final String url) {
        try
        {
            File file = new File(url);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = inputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip)AudioSystem.getLine(info);
            clip.open(inputStream);
            clip.start();
        }

        catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1)
        {
            e1.printStackTrace();
        }
    }
}