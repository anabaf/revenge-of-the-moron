package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Player;

import org.ac.proxymorons.revengeofthemoron.Game;
import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Character;
import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;
import org.ac.proxymorons.revengeofthemoron.Music;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class Player extends Character implements GameObjects {


    private Game game;

    private Background background;

    public Picture playerPicture;

    public Picture heartPicture1;

    public Picture heartPicture2;

    public Picture heartPicture3;

    private static final int SPEED = 5;

    public Bullet bullet;

    private static LinkedList<Bullet> bullets;

    public int currentPlayerY;

    public int currentPlayerX;

    public static boolean isDead;

    private int health = 3;

    public Player(Game game) {
        this.game = game;
    }

    @Override
    public int health() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void heartPicInit() {
        Picture heart1 = new Picture(350, 20, ResourcesHandler.PREFIX + "heart.png");
        Picture heart2 = new Picture(400, 20, ResourcesHandler.PREFIX + "heart.png");
        Picture heart3 = new Picture(450, 20, ResourcesHandler.PREFIX + "heart.png");

        this.heartPicture1 = heart1;
        this.heartPicture2 = heart2;
        this.heartPicture3 = heart3;
        heart1.draw();
        heart2.draw();
        heart3.draw();

    }

    public Picture init() {
        Picture picture = new Picture(25, 290, ResourcesHandler.PREFIX + "stupid.png");
        this.playerPicture = picture;
        picture.draw();
        heartPicInit();
        return picture;
    }


    public void showCharacter(Background background) {
        setBackground(background);
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void setCurrentPlayerX(int currentPlayerX) {
        this.currentPlayerX = currentPlayerX;
    }

    public void setCurrentPlayerY(int currentPlayerY) {
        this.currentPlayerY = currentPlayerY;
    }


    public void gotHit() {
        Music porra = new Music(ResourcesHandler.PREFIX + "porra.wav");
        Music fullswear = new Music(ResourcesHandler.PREFIX + "fullswear.wav");
        Music caralho = new Music(ResourcesHandler.PREFIX + "caralho.wav");

        if (health == 3) {
            health--;
            heartPicture1.delete();
            porra.play(false);
            return;
        } else if (health == 2) {
            health--;
            heartPicture2.delete();
            caralho.play(false);
            return;
        } else if (health == 1) {
            health--;
            isDead = true;
            heartPicture3.delete();
            playerPicture.delete();
            fullswear.play(false);
            game.gameOver();
        }
    }

    public void moveUp() {
        if (playerPicture.getY() <= background.getBackgroundY()) {
            return;
        }
        playerPicture.translate(0, -SPEED);
        setCurrentPlayerY(playerPicture.getY());
    }

    public void moveDown() {
        if (playerPicture.getMaxY() >= background.getBackgroundHeight()) {
            return;
        }
        playerPicture.translate(0, SPEED);
        setCurrentPlayerX(playerPicture.getX());
    }

    public void shoot() {
        bullet = new Bullet(background, playerPicture.getMaxX(), playerPicture.getY());
        bullet.bulletShot();

        bullets.add(bullet);
    }

    public void update() {
        if (bullets == null) {
            bullets = new LinkedList<>(); //have to create a new instance of linked list bullet, and assign bullets
        }


        LinkedList<Bullet> bulletsCopy = new LinkedList<>(bullets);
        if (Player.bullets.size() != 0) {
            for (Bullet bullet : bulletsCopy) {
                bullet.update();
            }
        }
    }

    public static LinkedList<Bullet> getBullets() {
        return bullets;
    }

    public int getX() {
        return playerPicture.getX();
    }

    public int getMaxX() {
        return playerPicture.getMaxX();
    }

    public int getY() {
        return playerPicture.getY();
    }

    public int getMaxY() {
        return playerPicture.getMaxY();
    }

    public int getHeight() {
        return playerPicture.getHeight();
    }

    public int getWidth() {
        return playerPicture.getWidth();
    }
}
