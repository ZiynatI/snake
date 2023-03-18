package org.zi.snake;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Field field = new Field();
        GameEngine gameEngine = new GameEngine(field) {
            @Override
            protected void sleep(long ms) throws InterruptedException {
                Thread.sleep(ms);
            }
        };
        Thread directionThread = new DirectionThread(gameEngine);
        directionThread.setDaemon(true);
        directionThread.start();
        gameEngine.play();
    }
}
