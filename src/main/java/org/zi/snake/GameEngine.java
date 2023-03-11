package org.zi.snake;

import java.util.*;

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
        SnakesDirection nextDirection = getNextDirectionNew(input);
        field.setDirection(nextDirection);
        boolean ateApple = field.moveSnakeToEatApple();
        if (ateApple) {
            score++;
        }
        System.out.println(printField());
        return field.hasSnakeDidWrongMove();
    }

    private SnakesDirection getNextDirection(Scanner input) {
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

    private SnakesDirection getNextDirectionNew(Scanner input) {
        Map<SnakesDirection, List<Pair<String, SnakesDirection>>> mapOfPossibleDirections = getMapOfPossibleDirections();
        while (true) {
            String nextDir = input.next().toUpperCase();
            for (Pair<String, SnakesDirection> inputStAndDir : mapOfPossibleDirections.get(field.getDirection())) {
                if (inputStAndDir.left.equals(nextDir)) {
                    return inputStAndDir.right;
                }
            }
        }
    }

    private Map<SnakesDirection, List<Pair<String, SnakesDirection>>> getMapOfPossibleDirections() {
        Pair<String, SnakesDirection> up = new Pair<>("W", SnakesDirection.UP);
        Pair<String, SnakesDirection> left = new Pair<>("A", SnakesDirection.LEFT);
        Pair<String, SnakesDirection> down = new Pair<>("S", SnakesDirection.DOWN);
        Pair<String, SnakesDirection> right = new Pair<>("D", SnakesDirection.RIGHT);
        Map<SnakesDirection, List<Pair<String, SnakesDirection>>> mapOfPossibleDirections = new HashMap<>();
        mapOfPossibleDirections.put(SnakesDirection.UP, Arrays.asList(up, left, right));
        mapOfPossibleDirections.put(SnakesDirection.DOWN, Arrays.asList(down, left, right));
        mapOfPossibleDirections.put(SnakesDirection.RIGHT, Arrays.asList(right, up, down));
        mapOfPossibleDirections.put(SnakesDirection.LEFT, Arrays.asList(left, up, down));
        return mapOfPossibleDirections;
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
}
