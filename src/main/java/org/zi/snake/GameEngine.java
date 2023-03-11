package org.zi.snake;

import java.util.Locale;
import java.util.Scanner;

public class GameEngine {

    private final int SLEEPING_TIME = 500;

    private int score = 0;
    private Field field = new Field();

    public void play() throws InterruptedException {
        boolean gameOver = false;
        Scanner input = new Scanner(System.in);
        System.out.println(printField());
        while (!gameOver) {
            long startTime = System.currentTimeMillis();
            gameOver = playRound(input);
            long endTime = System.currentTimeMillis();
            Thread.sleep(SLEEPING_TIME /*-(endTime - startTime)*/);
        }
        System.out.println("Game over\nScore:" + score);
    }

    private boolean playRound(Scanner input) {
        SnakesDirection nextDirection = getNextDirection(input);
        field.setDirection(nextDirection);
        boolean ateApple = field.moveSnakeToEatApple();
        if (ateApple) {
            score++;
        }
        System.out.println(printField());
        return field.hasSnakeDidWrongMove();
    }

    public SnakesDirection getNextDirection(Scanner input) {
        String nextDir;
        switch (field.getDirection()) {
            case UP: {
                while (true) {
                    nextDir = input.next().toUpperCase();
                    switch (nextDir) {
                        case "W":
                            return SnakesDirection.UP;
                        case "A":
                            return SnakesDirection.LEFT;
                        case "D":
                            return SnakesDirection.RIGHT;
                    }
                }
            }
            case DOWN: {
                while (true) {
                    nextDir = input.next().toUpperCase();
                    switch (nextDir) {
                        case "A":
                            return SnakesDirection.LEFT;
                        case "S":
                            return SnakesDirection.DOWN;
                        case "D":
                            return SnakesDirection.RIGHT;
                    }
                }
            }
            case LEFT: {
                while (true) {
                    nextDir = input.next().toUpperCase();
                    switch (nextDir) {
                        case "A":
                            return SnakesDirection.LEFT;
                        case "W":
                            return SnakesDirection.UP;
                        case "S":
                            return SnakesDirection.DOWN;
                    }
                }
            }
            case RIGHT: {
                while (true) {
                    nextDir = input.next().toUpperCase();
                    switch (nextDir) {
                        case "W":
                            return SnakesDirection.UP;
                        case "S":
                            return SnakesDirection.DOWN;
                        case "D":
                            return SnakesDirection.RIGHT;
                    }
                }
            }
        }
        return null;
    }

    public String printField() {
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
}
