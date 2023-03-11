package org.zi.snake;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Field {
    private final int fieldSize = 16;

    private Pair<Integer, Integer> apple;

    private Queue<Pair<Integer, Integer>> snake;
    private Pair<Integer, Integer> snakesHead;

    private SnakesDirection direction;

    private boolean snakeDidWrongMove;

    private Random rnd;

    public Field() {
        this.apple = new Pair<>(7, 11);
        this.snake = generateSnake();
        this.snakesHead = new Pair<>(7, 5);
        rnd = new Random();
        this.direction=SnakesDirection.RIGHT;
    }

    private Pair<Integer, Integer> getNextHead() {
        switch (this.direction) {
            case RIGHT:
                return new Pair<>(snakesHead.left, snakesHead.right + 1);
            case LEFT:
                return new Pair<>(snakesHead.left, snakesHead.right - 1);
            case UP:
                return new Pair<>(snakesHead.left - 1, snakesHead.right);
            case DOWN:
                return new Pair<>(snakesHead.left + 1, snakesHead.right);
        }
        return null;
    }

    /**
     * returns true if snake eat apple ad false if not
     */
    public boolean moveSnakeToEatApple() {
        Pair<Integer, Integer> cellToMoveIn = getNextHead();
        if (didWrongMove(cellToMoveIn)) {
            this.snakeDidWrongMove = true;
            return false;
        }
        snakesHead = cellToMoveIn;
        snake.offer(cellToMoveIn);
        boolean ateApple;
        if (snakesHead.equals(apple)) {
            this.generateApple();
            ateApple = true;
        } else {
            snake.remove();
            ateApple = false;
        }
        snakesHead = cellToMoveIn;
        return ateApple;
    }


    private void generateApple() {
        Pair<Integer, Integer> applesLocation;
        do {
            applesLocation = new Pair<>(rnd.nextInt(fieldSize), rnd.nextInt(fieldSize));
        } while (snake.contains(applesLocation));
        this.apple = applesLocation;
    }

    private boolean didWrongMove(Pair<Integer, Integer> cellToMoveIn) {
        return snake.contains(cellToMoveIn) || (cellToMoveIn.getLeft() >= fieldSize ||
                cellToMoveIn.getLeft() < 0 ||
                cellToMoveIn.getRight() >= fieldSize ||
                cellToMoveIn.getRight() < 0);
    }


    /**
     * This method contributes to the appearance of the desired snake queue at the beginning of the game and is used
     * only in the field constructor
     */
    private static Queue<Pair<Integer, Integer>> generateSnake() {
        Queue<Pair<Integer, Integer>> result = new LinkedList<>();
        result.add(new Pair<>(7, 3));
        result.add(new Pair<>(7, 4));
        result.add(new Pair<>(7, 5));
        return result;
    }

    public void setDirection(SnakesDirection direction) {
        this.direction = direction;
    }

    public Pair<Integer, Integer> getApple() {
        return apple;
    }

    public Queue<Pair<Integer, Integer>> getSnake() {
        return snake;
    }

    public boolean hasSnakeDidWrongMove() {
        return snakeDidWrongMove;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public SnakesDirection getDirection() {
        return direction;
    }
}
