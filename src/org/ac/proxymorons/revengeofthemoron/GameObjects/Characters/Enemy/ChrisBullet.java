package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class ChrisBullet implements GameObjects {

    public Picture chrisBulletPicture;


    public Background background;

    int currentChrisBulletPictureX;

    public static LinkedList<ChrisBullet> chrisBullets = new LinkedList<>();


    public boolean isShooting = false;

    public ChrisBullet(Background background, int chrisEnemyX, int chrisEnemyY) {
        this.background = background;
        int randomNumChris = (int) (Math.random() * 10);
        if (randomNumChris == 0) {
            chrisBulletPicture = new Picture(chrisEnemyX, chrisEnemyY, ResourcesHandler.PREFIX + "pikachu.png");
        } else {
            chrisBulletPicture = new Picture(chrisEnemyX, chrisEnemyY, ResourcesHandler.PREFIX + "pokemon.png");

        }

        currentChrisBulletPictureX = chrisBulletPicture.getX();
    }

    public void bulletShot() {
        isShooting = true;
        activateChrisBullet();
    }

    public void activateChrisBullet() {
        chrisBulletPicture.draw();
    }

    public void moveChrisBullet() {
        chrisBulletPicture.translate(-10, 0);
        currentChrisBulletPictureX = chrisBulletPicture.getX();
    }

    public void update() {
        if (isShooting) {
        }
        moveChrisBullet();
        checkBoundary();
    }

    public boolean checkBoundary() {
        if (currentChrisBulletPictureX <= 10) {
            isShooting = false;
            return true;
        }
        return false;
    }


    public void deleteCurrentChrisBullet(ChrisBullet chrisBullet) {
        chrisBullets.remove(chrisBullet);
        chrisBullet.chrisBulletPicture.delete();
    }

    public void deleteChrisBullet() {
        for (int i = 0; i < ChrisBullet.chrisBullets.size(); i++) {
            if (ChrisBullet.chrisBullets.get(i).checkBoundary()) {
                ChrisBullet.chrisBullets.get(i).chrisBulletPicture.delete();
                ChrisBullet.chrisBullets.remove(i);
            }
        }
    }

    public int getX() {
        return chrisBulletPicture.getX();
    }


    public int getMaxX() {
        return chrisBulletPicture.getMaxX();
    }


    public int getY() {
        return chrisBulletPicture.getY();
    }


    public int getMaxY() {
        return chrisBulletPicture.getMaxY();
    }

    public int getHeight() {
        return chrisBulletPicture.getHeight();
    }

    public int getWidth() {
        return chrisBulletPicture.getWidth();
    }
}
