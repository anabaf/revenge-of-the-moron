package org.ac.proxymorons.revengeofthemoron.GameLogic;

import org.ac.proxymorons.revengeofthemoron.Game;
import org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy.*;
import org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Player.Bullet;
import org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Player.Player;
import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;
import org.ac.proxymorons.revengeofthemoron.Music;
import org.ac.proxymorons.revengeofthemoron.ResourcesHandler;

public class AnimationLoop {
    private Player player;

    private Levels level;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setLevels(Levels level) {
        this.level = level;
    }

    public void start(Background background) {
        while (true) {


            try {

                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!Game.gameStarts) {

            } else {
                Background.deleteStartPicture();

                Music.intromusic.stop();
                Music.play1.setLoop(true);
                Music.play1.play(false);

                updateGame(background);
                collisionChecker();
            }
        }

    }


    private void updateGame(Background background) { //updates the player (bullet movement) and enemy movement
        if (!Player.isDead) {
            level.update(background);
            player.update();
        }
    }

    private void collisionChecker() {
        //check collision between enemies bullets and player
        for (int i = 0; i < ChrisBullet.chrisBullets.size(); i++) {
            if (checkCollision(player, ChrisBullet.chrisBullets.get(i))) {
                handleCollision(player, ChrisBullet.chrisBullets.get(i));
            }
        }

        for (int i = 0; i < PedroBullet.pedroBullets.size(); i++) {
            if (checkCollision(player, PedroBullet.pedroBullets.get(i))) {
                handleCollision(player, PedroBullet.pedroBullets.get(i));
            }
        }

        for (int i = 0; i < RuiBullet.ruiBullets.size(); i++) {
            if (checkCollision(player, RuiBullet.ruiBullets.get(i))) {
                handleCollision(player, RuiBullet.ruiBullets.get(i));
            }
        }

        for (int i = 0; i < MikeBullet.mikeBullets.size(); i++) {
            if (checkCollision(player, MikeBullet.mikeBullets.get(i))) {
                handleCollision(player, MikeBullet.mikeBullets.get(i));
            }
        }

        for (int i = 0; i < MarianaBullet.marianaBullets.size(); i++) {
            if (checkCollision(player, MarianaBullet.marianaBullets.get(i))) {
                handleCollision(player, MarianaBullet.marianaBullets.get(i));
            }
        }


        // check collision between bullets and enemies
        for (int i = 0; i < Player.getBullets().size(); i++) {
            Bullet bullet = Player.getBullets().get(i);
            for (int j = 0; j < level.getEnemies().size(); j++) {
                Enemy enemy = level.getEnemies().get(j);

                if (checkCollision(bullet, enemy)) {
                    handleCollision(bullet, enemy);
                }
            }
        }


        for (int i = 0; i < Player.getBullets().size(); i++) {
            Bullet bullet = Player.getBullets().get(i);
            for (int j = 0; j < PedroBullet.pedroBullets.size(); j++) {
                PedroBullet pedroBullet = PedroBullet.pedroBullets.get(j);

                if (checkCollision(bullet, pedroBullet)) {
                    handleCollision(bullet, pedroBullet);
                }
            }
        }

        for (int i = 0; i < Player.getBullets().size(); i++) {
            Bullet bullet = Player.getBullets().get(i);
            for (int j = 0; j < ChrisBullet.chrisBullets.size(); j++) {
                ChrisBullet chrisBullet = ChrisBullet.chrisBullets.get(j);

                if (checkCollision(bullet, chrisBullet)) {
                    handleCollision(bullet, chrisBullet);
                }
            }
        }

        for (int i = 0; i < Player.getBullets().size(); i++) {
            Bullet bullet = Player.getBullets().get(i);
            for (int j = 0; j < RuiBullet.ruiBullets.size(); j++) {
                RuiBullet ruiBullet = RuiBullet.ruiBullets.get(j);

                if (checkCollision(bullet, ruiBullet)) {
                    handleCollision(bullet, ruiBullet);
                }
            }
        }


        for (int i = 0; i < Player.getBullets().size(); i++) {
            Bullet bullet = Player.getBullets().get(i);
            for (int j = 0; j < MikeBullet.mikeBullets.size(); j++) {
                MikeBullet mikeBullet = MikeBullet.mikeBullets.get(j);

                if (checkCollision(bullet, mikeBullet)) {
                    handleCollision(bullet, mikeBullet);
                }
            }
        }

        for (int i = 0; i < Player.getBullets().size(); i++) {
            Bullet bullet = Player.getBullets().get(i);
            for (int j = 0; j < MarianaBullet.marianaBullets.size(); j++) {
                MarianaBullet marianaBullet = MarianaBullet.marianaBullets.get(j);

                if (checkCollision(bullet, marianaBullet)) {
                    handleCollision(bullet, marianaBullet);
                }
            }
        }


    }

    private boolean checkCollision(GameObjects object1, GameObjects object2) {

        int object1X = object1.getX();
        int object1MaxX = object1.getMaxX();
        int object1Y = object1.getY();
        int object1MaxY = object1.getMaxY();
        int object1Height = object1.getHeight();
        int object1Width = object1.getWidth();

        int object2X = object2.getX();
        int object2MaxX = object2.getMaxX();
        int object2Y = object2.getY();
        int object2MaxY = object2.getMaxY();
        int object2Height = object2.getHeight();
        int object2Width = object2.getWidth();

        if (object1MaxX >= object2X && object1X <= object2MaxX &&
                object1MaxY >= object2Y && object1Y <= object2MaxY) {

            return true;
        }

        return false; // No collision

    }


    private void handleCollision(Bullet bullet, Enemy enemy) {
        enemy.gotHit();
        bullet.deleteBullet();
    }

    private void handleCollision(Player player, ChrisBullet chrisBullet) {
        chrisBullet.deleteCurrentChrisBullet(chrisBullet);
        player.gotHit();
    }

    private void handleCollision(Player player, PedroBullet pedroBullet) {
        pedroBullet.deleteCurrentPedroBullet(pedroBullet);
        player.gotHit();
    }

    private void handleCollision(Player player, RuiBullet ruiBullet) {
        ruiBullet.deleteCurrentRuiBullet(ruiBullet);
        player.gotHit();
    }

    private void handleCollision(Player player, MikeBullet mikeBullet) {
        mikeBullet.deleteCurrentMikeBullet(mikeBullet);
        player.gotHit();
    }

    private void handleCollision(Player player, MarianaBullet marianaBullet) {
        marianaBullet.deleteCurrentMarianaBullet(marianaBullet);
        player.gotHit();
    }

    //bullet collision
    private void handleCollision(Bullet bullet, ChrisBullet chrisBullet) {
        bullet.deleteCurrentBullet(bullet);
        chrisBullet.deleteCurrentChrisBullet(chrisBullet);
    }

    private void handleCollision(Bullet bullet, PedroBullet pedroBullet) {
        bullet.deleteCurrentBullet(bullet);
        pedroBullet.deleteCurrentPedroBullet(pedroBullet);
    }

    private void handleCollision(Bullet bullet, RuiBullet ruiBullet) {
        bullet.deleteCurrentBullet(bullet);
        ruiBullet.deleteCurrentRuiBullet(ruiBullet);
    }


    private void handleCollision(Bullet bullet, MikeBullet mikeBullet) {
        bullet.deleteCurrentBullet(bullet);
        mikeBullet.deleteCurrentMikeBullet(mikeBullet);
    }

    private void handleCollision(Bullet bullet, MarianaBullet marianaBullet) {
        bullet.deleteCurrentBullet(bullet);
        marianaBullet.deleteCurrentMarianaBullet(marianaBullet);
    }
}
