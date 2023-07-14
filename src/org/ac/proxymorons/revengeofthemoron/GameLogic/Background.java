package org.ac.proxymorons.revengeofthemoron.GameLogic;

import org.ac.proxymorons.revengeofthemoron.Music;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Background {

    public Rectangle rectangle;

    public Picture picture;

    private static String scoreString;

    public static Text SCORETEXT;

    public static Picture startPicture;

    public  Picture startPictureInit() {
        Picture startBackground = new Picture(0, 0, ResourcesHandler.PREFIX + "start-screen.png");
        startBackground.draw();
        Music.intromusic.play(false);

        startPicture = startBackground;
        return  startPicture;
    }

    public static void deleteStartPicture() {
        startPicture.delete();
    }

    public Picture initPicture() {
        Picture backgroundPicture = new Picture(0,0, ResourcesHandler.PREFIX + "arena.png");
        this.picture = backgroundPicture;
        backgroundPicture.draw();
        return backgroundPicture;
    }

    public static Text scoreTextInit() {
            scoreString = "Score: " + Levels.SCORE;
            Text scoreText = new Text(400, 565, scoreString);
            SCORETEXT = scoreText;
            scoreText.grow(23,23);
            scoreText.draw();
            return scoreText;
        }


    public Rectangle init() {
        Rectangle rectangle = new Rectangle(10, 10, 800, 600);
        this.rectangle = rectangle;
        rectangle.draw();
        initPicture();
        scoreTextInit();
        return rectangle;
    }


    public int getBackgroundX() {
        return rectangle.getX();
    }

    public int getBackgroundY() {
        return rectangle.getY();
    }

    public int getBackgroundWidth() {
        return rectangle.getWidth();
    }

    public int getBackgroundHeight() {
        return rectangle.getHeight();
    }
}
