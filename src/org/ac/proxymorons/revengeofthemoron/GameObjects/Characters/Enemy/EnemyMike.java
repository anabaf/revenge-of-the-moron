package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class EnemyMike extends Enemy {

    private int health = 1;

    private int speed = 6;

    private Background background;

    public Picture enemyMikePicture;

    public MikeBullet mikeBullet;


    private boolean hasMikeBulletsClear = false;

    public boolean hasHitDownWall = false;

    private boolean isDead;

    @Override
    public int health() {
        return health;
    }

    public void removePic() {
        enemyMikePicture.delete();
    };

    public boolean getIsDead() {
        return isDead;

    }


    public EnemyMike(Background background) {
        Picture picture = new Picture(725, 290, ResourcesHandler.PREFIX + "mike.png");
        this.enemyMikePicture = picture;
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
            enemyMikePicture.delete();
        }
    }

    public void moveUp() {
        if (enemyMikePicture.getY() <= background.getBackgroundY()) {
            return;
        }
        enemyMikePicture.translate(0, -speed);
    }

    public void moveDown() {
        if (enemyMikePicture.getMaxY() >= background.getBackgroundHeight()) {
            hasHitDownWall = true;
            return;
        }
        enemyMikePicture.translate(0, speed);
    }


    public void shoot() {
        int randomShoot = (int) (Math.random() * 20);
        if (randomShoot == 0) {
            mikeBullet = new MikeBullet(background, enemyMikePicture.getX(), enemyMikePicture.getY());
            mikeBullet.bulletShot();
            MikeBullet.mikeBullets.add(mikeBullet);
        }
    }

    public void update() {
        if (MikeBullet.mikeBullets.size() != 0) {
            for (int i = 0; i < MikeBullet.mikeBullets.size(); i++) {
                MikeBullet.mikeBullets.get(i).update();
            }
        }
        deleteMikeBullet();


        if (isDead) {
            return;
        }
        shoot();


        if (enemyMikePicture.getY() <= 10) {
            hasHitDownWall = false;
        }
        if (!hasHitDownWall) {
            moveDown();
        } else {
            moveUp();
        }
    }


       public void deleteMikeBullet() {
            for (int i = 0; i < MikeBullet.mikeBullets.size(); i++) {
                if (MikeBullet.mikeBullets.get(i).checkBoundary()) {
                    MikeBullet.mikeBullets.get(i).mikeBulletPicture.delete();
                    MikeBullet.mikeBullets.remove(i);
                }
            }
        }


    public static LinkedList<MikeBullet> getMikeBullet() {
        return MikeBullet.mikeBullets;
    }


    public int getX() {
        return enemyMikePicture.getX();
    }

    public int getMaxX() {
        return enemyMikePicture.getMaxX();
    }

    public int getY() {
        return enemyMikePicture.getY();
    }

    public int getMaxY() {
        return enemyMikePicture.getMaxY();
    }

    public int getHeight() {
        return enemyMikePicture.getHeight();
    }

    public int getWidth() {
        return enemyMikePicture.getWidth();
    }
}




