package org.zi.snake;

import java.util.Scanner;

public class GameEngine {

    private final int SLEEPING_TIME = 500;

    private int score = 0;
    private Field field = new Field();

    public void play() throws InterruptedException {
        boolean gameOver = false;
        Scanner input = new Scanner(System.in);
        System.out.println(field.toString());
        while (!gameOver) {
            long startTime = System.currentTimeMillis();
            gameOver = playRound(input);
            long endTime = System.currentTimeMillis();
            Thread.sleep(SLEEPING_TIME - (endTime - startTime));
        }
        System.out.println("Game over\nScore:" + score);
    }

    private boolean playRound(Scanner input) {
        SnakesDirection nextDirection = getNextDirection(input);
        field.setDirection(nextDirection);
        field.moveSnake();
        if (field.ateApple()) {
            score++;
            field.setFalseAteApple();
        }
        System.out.println(field.toString());
        return field.hasSnakeDidWrongMove();
    }

    public static SnakesDirection getNextDirection(Scanner input) {
        do {
            String nextDir = input.next();
            switch (nextDir) {
                case "W":
                    return SnakesDirection.UP;
                case "A":
                    return SnakesDirection.LEFT;
                case "S":
                    return SnakesDirection.DOWN;
                case "D":
                    return SnakesDirection.RIGHT;
            }
        } while (true);
    }
}
