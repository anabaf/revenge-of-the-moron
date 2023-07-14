package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class EnemyMariana extends Enemy {

    private int health = 1;

    private int speed = 8;

    private Background background;

    public Picture enemyMarianaPicture;

    public MarianaBullet marianaBullet;


    private boolean hasMarianaBulletsClear = false;

    public boolean hasHitDownWall = false;

    private boolean isDead;

    @Override
    public int health() {
        return health;
    }


    public void removePic() {
        enemyMarianaPicture.delete();
    };

    public boolean getIsDead() {
        return isDead;

    }


    public EnemyMariana(Background background) {
        Picture picture = new Picture(725, 290, ResourcesHandler.PREFIX + "mariana.png");
        this.enemyMarianaPicture = picture;
        picture.draw();
        setBackground(background);
        showCharacter(background);
    }


    public void showCharacter(Background background) {
        setBackground(background);
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void gotHit() {
        if (health > 0) {
            health--;
        } else {
            isDead = true;
            enemyMarianaPicture.delete();
        }
    }

    public void moveUp() {
        if (enemyMarianaPicture.getY() <= background.getBackgroundY()) {
            return;
        }
        enemyMarianaPicture.translate(0, -speed);
    }

    public void moveDown() {
        if (enemyMarianaPicture.getMaxY() >= background.getBackgroundHeight()) {
            hasHitDownWall = true;
            return;
        }
        enemyMarianaPicture.translate(0, speed);
    }


    public void shoot() {
        int randomShoot = (int) (Math.random() * 5);
        if (randomShoot == 0) {
            marianaBullet = new MarianaBullet(background, enemyMarianaPicture.getX(), enemyMarianaPicture.getY());
            marianaBullet.bulletShot();
            MarianaBullet.marianaBullets.add(marianaBullet);
        }
    }

    public void update() {
        if (MarianaBullet.marianaBullets.size() != 0) {
            for (int i = 0; i < MarianaBullet.marianaBullets.size(); i++) {
                MarianaBullet.marianaBullets.get(i).update();
            }
        }
        deleteMarianaBullet();


        if (isDead) {
            return;
        }
        shoot();


        if (enemyMarianaPicture.getY() <= 10) {
            hasHitDownWall = false;
        }
        if (!hasHitDownWall) {
            moveDown();
        } else {
            moveUp();
        }
    }


    public void deleteMarianaBullet() {
        for (int i = 0; i < MarianaBullet.marianaBullets.size(); i++) {
            if (MarianaBullet.marianaBullets.get(i).checkBoundary()) {
                MarianaBullet.marianaBullets.get(i).marianaBulletPicture.delete();
                MarianaBullet.marianaBullets.remove(i);
            }
        }
    }


    public static LinkedList<MarianaBullet> getMarianaBullet() {
        return MarianaBullet.marianaBullets;
    }


    public int getX() {
        return enemyMarianaPicture.getX();
    }

    public int getMaxX() {
        return enemyMarianaPicture.getMaxX();
    }

    public int getY() {
        return enemyMarianaPicture.getY();
    }

    public int getMaxY() {
        return enemyMarianaPicture.getMaxY();
    }

    public int getHeight() {
        return enemyMarianaPicture.getHeight();
    }

    public int getWidth() {
        return enemyMarianaPicture.getWidth();
    }
}




