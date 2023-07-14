package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Player;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.ac.proxymorons.revengeofthemoron.Music;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Bullet implements GameObjects {

    public Picture bulletPicture;

    public Background background;


    public int currentBulletX;



    public boolean isShooting = false;

    public Bullet(Background background,int playerMaxX ,int playerY) {
        this.background = background;
        bulletPicture = new Picture(playerMaxX, playerY, ResourcesHandler.PREFIX +"rubber-duck.png");
        currentBulletX = bulletPicture.getX();
    }


    public void setCurrentBulletX(int currentBulletX) {
        this.currentBulletX = currentBulletX;
    }


    public void bulletShot() {
        isShooting = true;
        activateBullet();
        /*Music rubberduck = new Music(ResourcesHandler.PREFIX +"rubberduck.wav");
        rubberduck.play(false);*/
        Music turtle = new Music(ResourcesHandler.PREFIX +"turtle.wav");
        turtle.play(false);
    }


    public void activateBullet() {
        bulletPicture.draw();
    }

    public void moveBullet() {
            bulletPicture.translate(10, 0);
            setCurrentBulletX(bulletPicture.getX());
    }


    public void update() {
        if (isShooting) {
        }
        moveBullet();
        checkBoundary();
    }

    public boolean checkBoundary() {
        if(currentBulletX >= background.getBackgroundX() + background.getBackgroundWidth()) {
            isShooting = false;
            return true;
        }
        return false;
    }

    public void deleteCurrentBullet(Bullet bullet) {
        Player.getBullets().remove(bullet);
        bullet.bulletPicture.delete();
    }

    public void deleteBullet() {
        for (int i = 0; i < Player.getBullets().size(); i++) {
            if (Player.getBullets().get(i).checkBoundary()) {
                Player.getBullets().get(i).bulletPicture.delete();
                Player.getBullets().remove(i);
            }
        }
    }

    public int getX() {
        return bulletPicture.getX();
    }

    public int getMaxX() {
        return bulletPicture.getMaxX();
    }

    public int getY() {
        return bulletPicture.getY();
    }

    public int getMaxY() {
        return bulletPicture.getMaxY();
    }

    public int getHeight() {
        return bulletPicture.getHeight();
    }

    public int getWidth() {
        return bulletPicture.getWidth();
    }
}
