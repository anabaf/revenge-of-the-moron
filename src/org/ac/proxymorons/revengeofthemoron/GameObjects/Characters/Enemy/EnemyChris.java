package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class EnemyChris extends Enemy {

    private int health = 1;

    private int speed = 5;

    private Background background;

    public Picture enemyChrisPicture;

    public ChrisBullet chrisBullet;


    private boolean hasChrisBulletsClear = false;

    public boolean hasHitDownWall = false;

    private boolean isDead;

    public void removePic() {
         enemyChrisPicture.delete();
    };

    @Override
    public int health() {
        return health;
    }


    public boolean getIsDead() {
        return isDead;

    }


    public EnemyChris(Background background) {
        Picture picture = new Picture(725, 290, ResourcesHandler.PREFIX +"chris.png");
        this.enemyChrisPicture = picture;
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
            enemyChrisPicture.delete();
        }
    }

    public void moveUp() {
        if (enemyChrisPicture.getY() <= background.getBackgroundY()) {
            return;
        }
        enemyChrisPicture.translate(0, -speed);
    }

    public void moveDown() {
        if (enemyChrisPicture.getMaxY() >= background.getBackgroundHeight()) {
            hasHitDownWall = true;
            return;
        }
        enemyChrisPicture.translate(0, speed);
    }


    public void shoot() {
        int randomShoot = (int) (Math.random() * 50);
        if (randomShoot == 0) {
            chrisBullet = new ChrisBullet(background, enemyChrisPicture.getX(), enemyChrisPicture.getY());
            chrisBullet.bulletShot();
            ChrisBullet.chrisBullets.add(chrisBullet);
        }
    }

    public void update() {
        if (ChrisBullet.chrisBullets.size() != 0) {
            for (int i = 0; i < ChrisBullet.chrisBullets.size(); i++) {
                ChrisBullet.chrisBullets.get(i).update();
            }
        }
        deleteChrisBullet();

        if (isDead) {
            return;
        }
        shoot();



        if (enemyChrisPicture.getY() <= 10) {
            hasHitDownWall = false;
        }
        if (!hasHitDownWall) {
            moveDown();
        } else {
            moveUp();
        }
    }


    public void deleteChrisBullet() {
        for (int i = 0; i < ChrisBullet.chrisBullets.size(); i++) {
            if (ChrisBullet.chrisBullets.get(i).checkBoundary()) {
                ChrisBullet.chrisBullets.get(i).chrisBulletPicture.delete();
                ChrisBullet.chrisBullets.remove(i);
            }
        }
    }


    public static LinkedList<ChrisBullet> getChrisBullet() {
        return ChrisBullet.chrisBullets;
    }


    public int getX() {
        return enemyChrisPicture.getX();
    }

    public int getMaxX() {
        return enemyChrisPicture.getMaxX();
    }

    public int getY() {
        return enemyChrisPicture.getY();
    }

    public int getMaxY() {
        return enemyChrisPicture.getMaxY();
    }

    public int getHeight() {
        return enemyChrisPicture.getHeight();
    }

    public int getWidth() {
        return enemyChrisPicture.getWidth();
    }
}


