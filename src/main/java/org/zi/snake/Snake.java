package org.zi.snake;

import java.util.*;

public class Snake {
    private Queue<Pair<Integer, Integer>> snakesLocation;
    private Direction direction;
    private String input;
    private int score;
    private boolean snakeCameWrong = false;


    Pair<Integer, Integer> snakesHead = new Pair<>(7, 5);

    public Snake() {
        this.snakesLocation = startingSnake();
        this.direction = Direction.RIGHT;
        this.score = 0;
    }

    public void move(Field field) {
        Pair<Integer, Integer> cellToMoveIn = new Pair<>(0, 0);
        switch (this.direction) {
            case RIGHT:
                cellToMoveIn = new Pair<>(snakesHead.left, snakesHead.right + 1);
                break;
            case LEFT:
                cellToMoveIn = new Pair<>(snakesHead.left, snakesHead.right - 1);
                break;
            case UP:
                cellToMoveIn = new Pair<>(snakesHead.left - 1, snakesHead.right);
                break;
            case DOWN:
                cellToMoveIn = new Pair<>(snakesHead.left + 1, snakesHead.right);
                break;
        }
        if (didWrongMove(cellToMoveIn, field)) {
            this.snakeCameWrong = true;
            return;
        }
        snakesLocation.offer(cellToMoveIn);
        snakesHead = cellToMoveIn;
        if (field.getApple().equals(snakesHead)) {
            field.setAnApple(false);
            field.generateApple(snakesLocation);
            this.score++;
        } else {
            snakesLocation.remove();
        }
    }

    private boolean didWrongMove(Pair<Integer, Integer> cellToMoveIn, Field field) {
        if (this.snakesLocation.contains(cellToMoveIn)) {
            return true;
        } else if (cellToMoveIn.getLeft() > 15 || cellToMoveIn.getLeft() < 0 || cellToMoveIn.getRight() > 15 || cellToMoveIn.getRight() < 0) {
            return true;
        }
        return false;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Queue<Pair<Integer, Integer>> getSnakesLocation() {
        return this.snakesLocation;
    }

    public static Deque<Pair<Integer, Integer>> startingSnake() {
        Deque<Pair<Integer, Integer>> result = new LinkedList<>();
        result.add(new Pair<>(7, 3));
        result.add(new Pair<>(7, 4));
        result.add(new Pair<>(7, 5));
        return result;
    }

    public boolean didWrongMove() {
        return snakeCameWrong;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
