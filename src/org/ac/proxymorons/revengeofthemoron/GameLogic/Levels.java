package org.ac.proxymorons.revengeofthemoron.GameLogic;

import org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy.*;
import org.ac.proxymorons.revengeofthemoron.Music;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;

import java.util.LinkedList;

public class Levels {

    public static LinkedList<Enemy> enemies;

    public Enemy enemy;

    public static int SCORE = 0;

    public static int LEVEL = 0;


    public void currentLevel(Background background) {
        if (enemies == null) {
            enemies = new LinkedList<>();
            generateEnemy(background);
        }
        for (Enemy enemy : enemies) {
            if (enemy == null) {
                generateEnemy(background);
            }
        }
    }

    public void generateEnemy(Background background) {
        int randomEnemy = (int) (Math.random() * 5);
        //int randomEnemy = 4;
        switch (randomEnemy) {
            case 0:
                enemy = new EnemyChris(background);
                enemies.add(enemy);
                Music chrisSpawn = new Music(ResourcesHandler.PREFIX + "chris-spawn.wav");
                chrisSpawn.play(false);

                break;
            case 1:
                enemy = new EnemyPedro(background);
                enemies.add(enemy);
                Music pedroSpawn = new Music(ResourcesHandler.PREFIX + "pedro-spawn.wav");
                pedroSpawn.play(false);
                break;
            case 2:
                enemy = new EnemyRui(background);
                enemies.add(enemy);
                Music ruiSpawn = new Music(ResourcesHandler.PREFIX + "rui-spawn.wav");
                ruiSpawn.play(false);
                break;
            case 3:
                enemy = new EnemyMike(background);
                Music mikeSpawn = new Music(ResourcesHandler.PREFIX + "mike-spawn.wav");
                mikeSpawn.play(false);
                enemies.add(enemy);
                break;
            case 4:
                enemy = new EnemyMariana(background);
                Music marianaSpawn = new Music(ResourcesHandler.PREFIX + "mariana-spawn.wav");
                marianaSpawn.play(false);
                enemies.add(enemy);
                break;
        }
    }

    public void update(Background background) {

        currentLevel(background);

        Music.play1.play(false);

        for (Enemy enemy : enemies) {
            enemy.update();

            if (enemy.getIsDead() == true) {

                if (EnemyChris.getChrisBullet().size() == 0 && EnemyPedro.getPedroBullet().size() == 0 && EnemyRui.getRuiBullet().size() == 0
                        && EnemyMike.getMikeBullet().size() == 0 && EnemyMariana.getMarianaBullet().size() == 0) {
                    Music.play1.stop();
                    SCORE++;
                    Background.SCORETEXT.delete();
                    Background.scoreTextInit();
                    enemies.remove(enemy);
                    generateEnemy(background);
                    LEVEL++;
                }
            }
        }
    }

    public static LinkedList<Enemy> getEnemies() {
        return enemies;
    }

}
