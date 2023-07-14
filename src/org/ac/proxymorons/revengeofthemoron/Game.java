package org.ac.proxymorons.revengeofthemoron;

import org.ac.proxymorons.revengeofthemoron.GameLogic.AnimationLoop;
import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.GameLogic.Levels;
import org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy.*;
import org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Player.Player;
import org.ac.proxymorons.revengeofthemoron.Keyboard.MyKeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game {

    public static boolean gameStarts;

    public static boolean gameOver;

    public Player player;

    public Picture gameOverScreen;


    public void startGame() {
        Background background = new Background();
        background.init();

        Levels levels = new Levels();

        Player player1 = new Player(this);
        player1.init();
        background.startPictureInit();
        player1.showCharacter(background);
        this.player = player1;

        MyKeyboardHandler myKeyboardHandler = new MyKeyboardHandler(player1, this);
        myKeyboardHandler.init();

        AnimationLoop loop = new AnimationLoop();
        loop.setPlayer(player1);
        loop.setLevels(levels);

        loop.start(background);
    }



    public void gameOver() {
        gameOver = true;
        Music.play1.stop();
        Picture gameOverScreen = new Picture(0, 0, ResourcesHandler.PREFIX + "gameover-screen.png");
        this.gameOverScreen = gameOverScreen;
        gameOverScreen.draw();
        gameStarts = false;
    }


    public void restart() {
        gameOver = false;
        player.isDead = false;


        player.setHealth(3);
        gameOverScreen.delete();
        player.playerPicture.draw();
        player.heartPicInit();

        for(int i = 0; i < Player.getBullets().size(); i++) {
            Player.getBullets().get(i).bulletPicture.delete();
            Player.getBullets().remove(i);
        }


        for(int i = 0; i < Levels.enemies.size(); i++) {
            Levels.enemies.get(i).removePic();
            Levels.enemies.remove(i);
        }
        Levels.enemies = null;

        Levels.SCORE = 0;
        Levels.LEVEL = 0;
        Background.SCORETEXT.delete();
        Background.scoreTextInit();


        if (EnemyChris.getChrisBullet().size() != 0) {
            for (int i = 0; i < ChrisBullet.chrisBullets.size(); i++) {
                ChrisBullet.chrisBullets.get(i).chrisBulletPicture.delete();
                ChrisBullet.chrisBullets.remove(i);
                i--;
            }
        }

        if (EnemyPedro.getPedroBullet().size() != 0) {
            for (int i = 0; i < PedroBullet.pedroBullets.size(); i++) {
                PedroBullet.pedroBullets.get(i).pedroBulletPicture.delete();
                PedroBullet.pedroBullets.remove(i);
                i--;
            }
        }

        if (EnemyRui.getRuiBullet().size() != 0) {
            for (int i = 0; i < RuiBullet.ruiBullets.size(); i++) {
                RuiBullet.ruiBullets.get(i).ruiBulletPicture.delete();
                RuiBullet.ruiBullets.remove(i);
                i--;
            }
        }

        if (EnemyMike.getMikeBullet().size() != 0) {
            for (int i = 0; i < MikeBullet.mikeBullets.size(); i++) {
                MikeBullet.mikeBullets.get(i).mikeBulletPicture.delete();
                MikeBullet.mikeBullets.remove(i);
                i--;
            }
        }

        if (EnemyMariana.getMarianaBullet().size() != 0) {
            for (int i = 0; i < MarianaBullet.marianaBullets.size(); i++) {
                MarianaBullet.marianaBullets.get(i).marianaBulletPicture.delete();
                MarianaBullet.marianaBullets.remove(i);
                i--;
            }
        }

        gameStarts = true;
    }

}
