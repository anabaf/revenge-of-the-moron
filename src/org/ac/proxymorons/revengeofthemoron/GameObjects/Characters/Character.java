package org.ac.proxymorons.revengeofthemoron.GameObjects.Characters;

import org.ac.proxymorons.revengeofthemoron.GameObjects.GameObjects;


public abstract class Character implements GameObjects {

    public abstract int health();

    public boolean isDead(boolean x) {
        return false;
    }

    private boolean isDead;

}
