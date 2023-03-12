package org.zi.snake;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameEngine gameEngine = new GameEngine();
        gameEngine.play();
        gameEngine.playSecondThread();

    }
}
