package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class RuiBullet implements GameObjects {

    public Picture ruiBulletPicture;

    public Background background;

    int currentRuiBulletPictureX;

    public static LinkedList<RuiBullet> ruiBullets = new LinkedList<>();


    public boolean isShooting = false;

    public RuiBullet(Background background, int ruiEnemyX, int ruiEnemyY) {
        this.background = background;
        int randomNumRui = (int)(Math.random()*2);
        if(randomNumRui==0) {
            ruiBulletPicture = new Picture(ruiEnemyX, ruiEnemyY, ResourcesHandler.PREFIX + "beer.png");
        } else {
            ruiBulletPicture = new Picture(ruiEnemyX, ruiEnemyY, ResourcesHandler.PREFIX + "javabible.png");
        }
        currentRuiBulletPictureX = ruiBulletPicture.getX();
    }

    public void bulletShot() {
        isShooting = true;
        activateRuiBullet();
    }

    public void activateRuiBullet() {
        ruiBulletPicture.draw();
    }

    public void moveRuiBullet() {
        ruiBulletPicture.translate(-6, 0);
        currentRuiBulletPictureX = ruiBulletPicture.getX();
    }

    public void update() {
        if (isShooting) {
        }
        moveRuiBullet();
        checkBoundary();
    }

    public boolean checkBoundary() {
        if(currentRuiBulletPictureX <= 10) {
            isShooting = false;
            return true;
        }
        return false;
    }


    public void deleteCurrentRuiBullet(RuiBullet ruiBullet) {
        ruiBullets.remove(ruiBullet);
        ruiBullet.ruiBulletPicture.delete();
    }

    public void deleteRuiBullet() {
        for (int i = 0; i < RuiBullet.ruiBullets.size(); i++) {
            if (RuiBullet.ruiBullets.get(i).checkBoundary() ) {
                RuiBullet.ruiBullets.get(i).ruiBulletPicture.delete();
                RuiBullet.ruiBullets.remove(i);
            }
        }
    }

    public int getX() {
        return ruiBulletPicture.getX();
    }


    public int getMaxX() {
        return ruiBulletPicture.getMaxX();
    }


    public int getY() {
        return ruiBulletPicture.getY();
    }


    public int getMaxY() {
        return ruiBulletPicture.getMaxY();
    }

    public int getHeight() {
        return ruiBulletPicture.getHeight();
    }

    public int getWidth() {
        return ruiBulletPicture.getWidth();
    }
}
