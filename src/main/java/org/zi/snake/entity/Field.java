package org.zi.snake.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Field {
    private final int fieldSize = 16;

    private Pair<Integer, Integer> apple;

    private final Queue<Pair<Integer, Integer>> snake;
    private Pair<Integer, Integer> snakesHead;

    private Direction direction;

    private boolean snakeDidWrongMove;

    private final Random rnd;

    public Field(int seed) {
        this.apple = new Pair<>(7, 11);
        this.snake = generateSnake();
        this.snakesHead = new ArrayList<>(this.snake).get(this.snake.size() - 1);
        this.rnd = new Random(seed);
        this.direction = Direction.RIGHT;
    }

    private Pair<Integer, Integer> getNextHead() {
        switch (this.direction) {
            case RIGHT:
                return new Pair<>(snakesHead.getLeft(), snakesHead.getRight() + 1);
            case LEFT:
                return new Pair<>(snakesHead.getLeft(), snakesHead.getRight() - 1);
            case UP:
                return new Pair<>(snakesHead.getLeft() - 1, snakesHead.getRight());
            case DOWN:
                return new Pair<>(snakesHead.getLeft() + 1, snakesHead.getRight());
        }
        return null;
    }

    /**
     * returns true if snake eat apple and false if not
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
    protected Queue<Pair<Integer, Integer>> generateSnake() {
        Queue<Pair<Integer, Integer>> result = new LinkedList<>();
        result.add(new Pair<>(7, 3));
        result.add(new Pair<>(7, 4));
        result.add(new Pair<>(7, 5));
        return result;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Pair<Integer, Integer> getApple() {
        return apple;
    }

    public Queue<Pair<Integer, Integer>> getSnake() {
        return new LinkedList<>(snake);
    }

    public Pair<Integer, Integer> getSnakesHead() {
        return snakesHead;
    }

    public boolean hasSnakeDidWrongMove() {
        return snakeDidWrongMove;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public Direction getDirection() {
        return direction;
    }
}
