package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class EnemyRui extends Enemy {

    private int health = 1;

    private int speed = 6;

    private Background background;

    public Picture enemyRuiPicture;

    public RuiBullet ruiBullet;


    private boolean hasRuiBulletsClear = false;

    public boolean hasHitDownWall = false;

    private boolean isDead;

    @Override
    public int health() {
        return health;
    }


    public void removePic() {
        enemyRuiPicture.delete();
    };

    public boolean getIsDead() {
        return isDead;

    }


    public EnemyRui(Background background) {
        int randomY = (int) (Math.random() * background.getBackgroundHeight() - 10);
        Picture picture = new Picture(725, randomY, ResourcesHandler.PREFIX +"rui.png");
        this.enemyRuiPicture = picture;
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
            enemyRuiPicture.delete();
        }
    }

    public void moveUp() {
        if (enemyRuiPicture.getY() <= background.getBackgroundY()) {
            return;
        }
        enemyRuiPicture.translate(0, -speed);
    }

    public void moveDown() {
        if (enemyRuiPicture.getMaxY() >= background.getBackgroundHeight()) {
            hasHitDownWall = true;
            return;
        }
        enemyRuiPicture.translate(0, speed);
    }


    public void shoot() {
        int randomShoot = (int) (Math.random() * 20);
        if (randomShoot == 0) {
            ruiBullet = new RuiBullet(background, enemyRuiPicture.getX(), enemyRuiPicture.getY());
            ruiBullet.bulletShot();
            RuiBullet.ruiBullets.add(ruiBullet);
        }
    }

    public void update() {
        if (RuiBullet.ruiBullets.size() != 0) {
            for (int i = 0; i < RuiBullet.ruiBullets.size(); i++) {
                RuiBullet.ruiBullets.get(i).update();
            }
        }
        deleteRuiBullet();

        if (isDead) {
            return;
        }
        shoot();



        if (enemyRuiPicture.getY() <= 10) {
            hasHitDownWall = false;
        }
        if (!hasHitDownWall) {
            moveDown();
        } else {
            moveUp();
        }
    }


    public void deleteRuiBullet() {
        for (int i = 0; i < RuiBullet.ruiBullets.size(); i++) {
            if (RuiBullet.ruiBullets.get(i).checkBoundary()) {
                RuiBullet.ruiBullets.get(i).ruiBulletPicture.delete();
                RuiBullet.ruiBullets.remove(i);
            }
        }
    }


    public static LinkedList<RuiBullet> getRuiBullet() {
        return RuiBullet.ruiBullets;
    }


    public int getX() {
        return enemyRuiPicture.getX();
    }

    public int getMaxX() {
        return enemyRuiPicture.getMaxX();
    }

    public int getY() {
        return enemyRuiPicture.getY();
    }

    public int getMaxY() {
        return enemyRuiPicture.getMaxY();
    }

    public int getHeight() {
        return enemyRuiPicture.getHeight();
    }

    public int getWidth() {
        return enemyRuiPicture.getWidth();
    }
}


