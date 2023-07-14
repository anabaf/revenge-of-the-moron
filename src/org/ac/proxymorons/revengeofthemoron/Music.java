package org.ac.proxymorons.revengeofthemoron;


//Version for IntelliJ
//To do the Jar, comment this out. Also change resource handler to blank.

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Music {
    public static Music intromusic = new Music(ResourcesHandler.PREFIX + "intro-music.wav");
    public static Music play1 = new Music(ResourcesHandler.PREFIX + "play1.wav");
    //public static Music play2 = new Music(ResourcesHandler.PREFIX + "play2.wav");


    /*private Clip clip;
    private URL soundURL;
    private boolean loop;

    public Music(String path) {
        initClip(path);
    }

    public void play(boolean fromStart) {
        if (fromStart) {
            clip.setFramePosition(0);
        }
        clip.loop(loop ? Clip.LOOP_CONTINUOUSLY : 0);
    }

    public void stop() {
        clip.stop();
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    private void initClip(String path) {
        soundURL = Music.class.getResource(path);
        AudioInputStream inputStream = null;
        try {
            if (soundURL == null) {
                path = path.substring(0);
                File file = new File(path);
                soundURL = file.toURI().toURL();
            }
            inputStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}*/

// Version for Jar

    private Clip clip;
    private boolean loop;

    public Music(String path) {
        initClip("/" + path);
    }

    public void play(boolean fromStart) {
        if (fromStart) {
            clip.setFramePosition(0);
        }
        clip.loop(loop ? Clip.LOOP_CONTINUOUSLY : 0);
        clip.start();
    }

    public void stop() {
        clip.stop();
        clip.setFramePosition(0);
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    private void initClip(String path) {
        try {
            InputStream is = Music.class.getResourceAsStream(path);
            if (is == null) {
                throw new RuntimeException("Resource not found: " + path);
            }
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(bufferedIn);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            ex.printStackTrace();
        }
    }
}


