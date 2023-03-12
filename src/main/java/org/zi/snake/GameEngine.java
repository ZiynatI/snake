package org.zi.snake;

import java.util.*;

public class GameEngine {

    private final int SLEEPING_TIME = 1000;

    private int score = 0;

    public Field getField() {
        return field;
    }

    private Field field = new Field();

    public void play() throws InterruptedException {
        boolean gameOver = false;
        System.out.println(printField());
        while (!gameOver) {
            long startTime = System.currentTimeMillis();
            gameOver = playRound();
            long endTime = System.currentTimeMillis();
            Thread.sleep(SLEEPING_TIME - (endTime - startTime));
        }
        System.out.println("Game over\nScore:" + score);
    }

    private boolean playRound() {
        boolean ateApple = field.moveSnakeToEatApple();
        if (ateApple) {
            score++;
        }
        System.out.println(printField());
        return field.hasSnakeDidWrongMove();
    }

    private String printField() {
        StringBuilder sb = new StringBuilder();
        sb.append("__________________\n");
        for (int i = 0; i < field.getFieldSize(); i++) {
            sb.append("|");
            for (int j = 0; j < field.getFieldSize(); j++) {
                if (new Pair<>(i, j).equals(field.getApple())) {
                    sb.append("A");
                } else if (field.getSnake().contains(new Pair<>(i, j))) {
                    sb.append("O");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("|\n");
        }
        sb.append("__________________");
        return sb.toString();
    }

    public void setDirection(SnakesDirection direction) {
        field.setDirection(direction);
    }
}
