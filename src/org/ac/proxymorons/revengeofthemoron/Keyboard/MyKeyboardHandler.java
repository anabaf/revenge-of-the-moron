package org.ac.proxymorons.revengeofthemoron.Keyboard;

import org.ac.proxymorons.revengeofthemoron.Game;
import org.ac.proxymorons.revengeofthemoron.GameObjects.Characters.Player.Player;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class MyKeyboardHandler implements KeyboardHandler {

    private Keyboard keyboard;
    private Player player;
    private Game game;
    private long lastTimeShot = System.currentTimeMillis();
    public static final int DELAY = 200;


    public MyKeyboardHandler(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public void init() {
        keyboard = new Keyboard(this);
        int[] keys = new int[]{
                KeyboardEvent.KEY_UP,
                KeyboardEvent.KEY_DOWN,
                KeyboardEvent.KEY_SPACE,
                KeyboardEvent.KEY_ENTER,
        };


        for (int i = 0; i < keys.length; i++) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            event.setKey(keys[i]);
            keyboard.addEventListener(event);

        }
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_UP:
                player.moveUp();
                break;

            case KeyboardEvent.KEY_DOWN:
                player.moveDown();
                break;

            case KeyboardEvent.KEY_SPACE:
                if (Game.gameStarts) {
                    if (System.currentTimeMillis() - lastTimeShot > DELAY) {
                        lastTimeShot = System.currentTimeMillis();
                        player.shoot();
                    }
                }
                break;

            case KeyboardEvent.KEY_ENTER:
                if (!Game.gameOver) {
                    Game.gameStarts = true;
                    break;
                } else {
                    game.restart();
                    break;
                }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
