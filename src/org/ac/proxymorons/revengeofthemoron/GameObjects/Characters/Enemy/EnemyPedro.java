package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameLogic.Background;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.LinkedList;

public class EnemyPedro extends Enemy {

    private int health = 1;

    private int speed = 5;

    private Background background;

    public Picture enemyPedroPicture;

    public PedroBullet pedroBullet;


    private boolean hasPedroBulletsClear = false;

    public boolean hasHitDownWall = false;

    private boolean isDead;

    @Override
    public int health() {
        return health;
    }

    public void removePic() {
        enemyPedroPicture.delete();
    };

    public boolean getIsDead() {
        return isDead;

    }


    public EnemyPedro(Background background) {
        int randomY = (int) (Math.random() * background.getBackgroundHeight() - 10);
        Picture picture = new Picture(720, randomY, ResourcesHandler.PREFIX +"pedro.png");
        this.enemyPedroPicture = picture;
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
            enemyPedroPicture.delete();
        }
    }

    public void moveUp() {
        if (enemyPedroPicture.getY() <= background.getBackgroundY()) {
            return;
        }
        enemyPedroPicture.translate(0, -speed);
    }

    public void moveDown() {
        if (enemyPedroPicture.getMaxY() >= background.getBackgroundHeight()) {
            hasHitDownWall = true;
            return;
        }
        enemyPedroPicture.translate(0, speed);
    }


    public void shoot() {
        int randomShoot = (int) (Math.random() * 30);
        if (randomShoot == 0) {
            pedroBullet = new PedroBullet(background, enemyPedroPicture.getX(), enemyPedroPicture.getY());
            pedroBullet.bulletShot();
            PedroBullet.pedroBullets.add(pedroBullet);
        }
    }

    public void update() {
        if (PedroBullet.pedroBullets.size() != 0) {
            for (int i = 0; i < PedroBullet.pedroBullets.size(); i++) {
                PedroBullet.pedroBullets.get(i).update();
            }
        }
        deletePedroBullet();

        if (isDead) {
            return;
        }
        shoot();



        if (enemyPedroPicture.getY() <= 10) {
            hasHitDownWall = false;
        }
        if (!hasHitDownWall) {
            moveDown();
        } else {
            moveUp();
        }
    }


    public void deletePedroBullet() {
        for (int i = 0; i < PedroBullet.pedroBullets.size(); i++) {
            if (PedroBullet.pedroBullets.get(i).checkBoundary()) {
                PedroBullet.pedroBullets.get(i).pedroBulletPicture.delete();
                PedroBullet.pedroBullets.remove(i);
            }
        }
    }


    public static LinkedList<PedroBullet> getPedroBullet() {
        return PedroBullet.pedroBullets;
    }


    public int getX() {
        return enemyPedroPicture.getX();
    }

    public int getMaxX() {
        return enemyPedroPicture.getMaxX();
    }

    public int getY() {
        return enemyPedroPicture.getY();
    }

    public int getMaxY() {
        return enemyPedroPicture.getMaxY();
    }

    public int getHeight() {
        return enemyPedroPicture.getHeight();
    }

    public int getWidth() {
        return enemyPedroPicture.getWidth();
    }
}


