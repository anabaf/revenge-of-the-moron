package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class MarianaBullet implements GameObjects {

    public Picture marianaBulletPicture;


    public Background background;

    int currentMarianaBulletPictureX;

    public static LinkedList<MarianaBullet> marianaBullets = new LinkedList<>();


    public boolean isShooting = false;

    public MarianaBullet(Background background, int marianaEnemyX, int marianaEnemyY) {
        this.background = background;

            marianaBulletPicture = new Picture(marianaEnemyX, marianaEnemyY, ResourcesHandler.PREFIX + "teddy-bear.png");


        currentMarianaBulletPictureX = marianaBulletPicture.getX();
    }

    public void bulletShot() {
        isShooting = true;
        activateMarianaBullet();
    }

    public void activateMarianaBullet() {
        marianaBulletPicture.draw();
    }

    public void moveMarianaBullet() {
        marianaBulletPicture.translate(-15, 0);
        currentMarianaBulletPictureX = marianaBulletPicture.getX();
    }

    public void update() {
        if (isShooting) {
        }
        moveMarianaBullet();
        checkBoundary();
    }

    public boolean checkBoundary() {
        if (currentMarianaBulletPictureX <= 10) {
            isShooting = false;
            return true;
        }
        return false;
    }


    public void deleteCurrentMarianaBullet(MarianaBullet marianaBullet) {
        marianaBullets.remove(marianaBullet);
        marianaBullet.marianaBulletPicture.delete();
    }

    public void deleteMarianaBullet() {
        for (int i = 0; i < MarianaBullet.marianaBullets.size(); i++) {
            if (MarianaBullet.marianaBullets.get(i).checkBoundary()) {
                MarianaBullet.marianaBullets.get(i).marianaBulletPicture.delete();
                MarianaBullet.marianaBullets.remove(i);
            }
        }
    }

    public int getX() {
        return marianaBulletPicture.getX();
    }


    public int getMaxX() {
        return marianaBulletPicture.getMaxX();
    }


    public int getY() {
        return marianaBulletPicture.getY();
    }


    public int getMaxY() {
        return marianaBulletPicture.getMaxY();
    }

    public int getHeight() {
        return marianaBulletPicture.getHeight();
    }

    public int getWidth() {
        return marianaBulletPicture.getWidth();
    }
}
