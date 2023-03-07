package org.zi.snake;

import java.util.*;

public class Snake {
    private Deque<Pair<Integer, Integer>> snakesLocation;
    private Direction direction;
    private Scanner input;
    public int score;
    public boolean snakeCameWrong = false;
    Pair<Integer, Integer> snakesHead = new Pair<>(7, 5);

    public Snake(Scanner input) {
        this.snakesLocation = startingSnake();
        this.direction = Direction.RIGHT;
        this.input = input;
        this.score = 0;
    }

    public void move(Field field)  {
        Pair<Integer, Integer> cellToMoveIn = new Pair<>(0, 0);
        if (this.input.hasNext()) {
            String nextDir = this.input.next();
            switch (nextDir) {
                case "W":
                    this.direction = Direction.RIGHT;
                    cellToMoveIn = new Pair<>(snakesHead.left, snakesHead.right + 1);
                    break;
                case "A":
                    this.direction = Direction.LEFT;
                    cellToMoveIn = new Pair<>(snakesHead.left, snakesHead.right - 1);
                    break;
                case "S":
                    this.direction = Direction.UP;
                    cellToMoveIn = new Pair<>(snakesHead.left - 1, snakesHead.right);
                    break;
                case "D":
                    this.direction = Direction.DOWN;
                    cellToMoveIn = new Pair<>(snakesHead.left + 1, snakesHead.right);
                    break;
            }
        }
        if (snakeCameWrong(cellToMoveIn, field)) {
            this.snakeCameWrong = true;
            return;
        }
        snakesLocation.offer(cellToMoveIn);
        snakesHead = snakesLocation.getLast();
        if (field.apple.equals(snakesHead)) {
            field.hasAnApple = false;
            field.generateApple();
            this.score++;
        } else {
            snakesLocation.remove();
        }


    }

    public boolean snakeCameWrong(Pair<Integer, Integer> cellToMoveIn, Field field) {
        if (this.snakesLocation.contains(cellToMoveIn)) {
            return true;
        } else if (cellToMoveIn.getLeft() > 15 || cellToMoveIn.getLeft() < 0 || cellToMoveIn.getRight() > 15 || cellToMoveIn.getRight() < 0) {
            return true;
        }
        return false;
    }

    public Deque<Pair<Integer, Integer>> getSnakesLocation() {
        return this.snakesLocation;
    }

    public static Deque<Pair<Integer, Integer>> startingSnake() {
        Deque<Pair<Integer, Integer>> result = new LinkedList<>();
        result.add(new Pair<>(7, 3));
        result.add(new Pair<>(7, 4));
        result.add(new Pair<>(7, 5));
        return result;
    }

    public enum Direction {
        LEFT, RIGHT, UP, DOWN;
    }
}
