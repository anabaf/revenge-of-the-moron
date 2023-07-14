package org.ac.proxymorons.revengeofthemoron;

import org.academiadecodigo.simplegraphics.graphics.Canvas;

public class Main {
    public static void main(String[] args) {

        // limits the canvas to a set width and height
        Canvas.setMaxX(800);
        Canvas.setMaxY(600);

        Game game = new Game();

        game.startGame();

    }
}
