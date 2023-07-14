package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Enemy;

import org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Character;
import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;
import org.academiadecodigo.simplegraphics.pictures.Picture;

abstract public class Enemy extends Character implements GameObjects {

   private int health;

    public void gotHit() {};


    private boolean isDead;

    public boolean getIsDead() {
        return isDead;
    }

    public void removePic() {};



    public void update() {};
}
