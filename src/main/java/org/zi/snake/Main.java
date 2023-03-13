package org.zi.snake;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameEngine gameEngine = new GameEngine();
        Thread directionThread = new DirectionThread(gameEngine);
        directionThread.setDaemon(true);
        directionThread.start();
        gameEngine.play();
    }
}
