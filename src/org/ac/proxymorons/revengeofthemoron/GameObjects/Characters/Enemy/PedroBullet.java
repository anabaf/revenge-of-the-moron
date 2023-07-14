package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class PedroBullet implements GameObjects {

    public Picture pedroBulletPicture;

    public Background background;

    int currentPedroBulletPictureX;

    public static LinkedList<PedroBullet> pedroBullets = new LinkedList<>();


    public boolean isShooting = false;

    public PedroBullet(Background background, int pedroEnemyX, int pedroEnemyY) {
        this.background = background;
        int randomNumPedro = (int)(Math.random()*2);
        if(randomNumPedro==0) {
            pedroBulletPicture = new Picture(pedroEnemyX, pedroEnemyY, ResourcesHandler.PREFIX +"disco-ball.png");
        } else {
            pedroBulletPicture = new Picture(pedroEnemyX, pedroEnemyY, ResourcesHandler.PREFIX + "bye.png");
        }

        currentPedroBulletPictureX = pedroBulletPicture.getX();
    }

    public void bulletShot() {
        isShooting = true;
        activatePedroBullet();
    }

    public void activatePedroBullet() {
        pedroBulletPicture.draw();
    }

    public void movePedroBullet() {
        pedroBulletPicture.translate(-6, 0);
        currentPedroBulletPictureX = pedroBulletPicture.getX();
    }

    public void update() {
        if (isShooting) {
        }
        movePedroBullet();
        checkBoundary();
    }

    public boolean checkBoundary() {
        if(currentPedroBulletPictureX <= 10) {
            isShooting = false;
            return true;
        }
        return false;
    }


    public void deleteCurrentPedroBullet(PedroBullet pedroBullet) {
        pedroBullet.pedroBulletPicture.delete();
        pedroBullets.remove(pedroBullet);

    }

    public void deletePedroBullet() {
        for (int i = 0; i < PedroBullet.pedroBullets.size(); i++) {
            if (PedroBullet.pedroBullets.get(i).checkBoundary() ) {
                PedroBullet.pedroBullets.get(i).pedroBulletPicture.delete();
                PedroBullet.pedroBullets.remove(i);
            }
        }
    }

    public int getX() {
        return pedroBulletPicture.getX();
    }


    public int getMaxX() {
        return pedroBulletPicture.getMaxX();
    }


    public int getY() {
        return pedroBulletPicture.getY();
    }


    public int getMaxY() {
        return pedroBulletPicture.getMaxY();
    }

    public int getHeight() {
        return pedroBulletPicture.getHeight();
    }

    public int getWidth() {
        return pedroBulletPicture.getWidth();
    }
}
