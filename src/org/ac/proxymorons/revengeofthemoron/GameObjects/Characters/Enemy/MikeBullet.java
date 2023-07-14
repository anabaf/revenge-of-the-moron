package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class MikeBullet implements GameObjects {

    public Picture mikeBulletPicture;


    public Background background;

    int currentMikeBulletPictureX;

    public static LinkedList<MikeBullet> mikeBullets = new LinkedList<>();


    public boolean isShooting = false;

    public MikeBullet(Background background, int mikeEnemyX, int mikeEnemyY) {
        this.background = background;
        int randomNumMike = (int) (Math.random() * 2);
        if (randomNumMike == 0) {
            mikeBulletPicture = new Picture(mikeEnemyX, mikeEnemyY, ResourcesHandler.PREFIX + "weed.png");
        } else {
            mikeBulletPicture = new Picture(mikeEnemyX, mikeEnemyY, ResourcesHandler.PREFIX + "microfone.png");

        }

        currentMikeBulletPictureX = mikeBulletPicture.getX();
    }

    public void bulletShot() {
        isShooting = true;
        activateMikeBullet();
    }

    public void activateMikeBullet() {
        mikeBulletPicture.draw();
    }

    public void moveMikeBullet() {
        mikeBulletPicture.translate(-10, 0);
        currentMikeBulletPictureX = mikeBulletPicture.getX();
    }

    public void update() {
        if (isShooting) {
        }
        moveMikeBullet();
        checkBoundary();
    }

    public boolean checkBoundary() {
        if (currentMikeBulletPictureX <= 10) {
            isShooting = false;
            return true;
        }
        return false;
    }


    public void deleteCurrentMikeBullet(MikeBullet mikeBullet) {
        mikeBullets.remove(mikeBullet);
        mikeBullet.mikeBulletPicture.delete();
    }

    public void deleteMikeBullet() {
        for (int i = 0; i < MikeBullet.mikeBullets.size(); i++) {
            if (MikeBullet.mikeBullets.get(i).checkBoundary()) {
                MikeBullet.mikeBullets.get(i).mikeBulletPicture.delete();
                MikeBullet.mikeBullets.remove(i);
            }
        }
    }

    public int getX() {
        return mikeBulletPicture.getX();
    }


    public int getMaxX() {
        return mikeBulletPicture.getMaxX();
    }


    public int getY() {
        return mikeBulletPicture.getY();
    }


    public int getMaxY() {
        return mikeBulletPicture.getMaxY();
    }

    public int getHeight() {
        return mikeBulletPicture.getHeight();
    }

    public int getWidth() {
        return mikeBulletPicture.getWidth();
    }
}
