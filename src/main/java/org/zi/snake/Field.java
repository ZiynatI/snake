package org.zi.snake;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Field {
    private final int[][] field;
    private Pair<Integer, Integer> apple;
    private Queue<Pair<Integer, Integer>> snake;
    private SnakesDirection direction;
    private boolean snakeDidWrongMove;
    private Pair<Integer, Integer> snakesHead;

    public Field() {
        this.field = new int[16][16];
        this.apple = new Pair<>(7, 11);
        this.snake = generateSnake();
        this.snakesHead = new Pair<>(7, 5);
    }

    public Pair<Integer, Integer> findCellToMoveIn() {
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
        return cellToMoveIn;
    }

    public void moveSnake() {
        Pair<Integer, Integer> cellToMoveIn = findCellToMoveIn();
        if (didWrongMove(cellToMoveIn)) {
            this.snakeDidWrongMove = true;
            return;
        }
        snake.offer(cellToMoveIn);
        snakesHead = cellToMoveIn;
    }

    public void generateApple() {
        Pair<Integer, Integer> applesLocation;
        do {
            applesLocation = new Pair<>((int) (Math.random() * 17), (int) (Math.random() * 17));
        } while (snake.contains(applesLocation));
        this.apple = applesLocation;


    }

    private boolean didWrongMove(Pair<Integer, Integer> cellToMoveIn) {
        if (snake.contains(cellToMoveIn) || (cellToMoveIn.getLeft() > 15 || cellToMoveIn.getLeft() < 0 ||
                cellToMoveIn.getRight() > 15 || cellToMoveIn.getRight() < 0)) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("__________________\n");
        for (int i = 0; i < field.length; i++) {
            sb.append("|");
            for (int j = 0; j < field[i].length; j++) {
                if (new Pair<>(i, j).equals(apple)) {
                    sb.append("A");
                } else if (snake.contains(new Pair<>(i, j))) {
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

    public Pair<Integer, Integer> getApple() {
        return apple;
    }

    public static Deque<Pair<Integer, Integer>> generateSnake() {
        Deque<Pair<Integer, Integer>> result = new LinkedList<>();
        result.add(new Pair<>(7, 3));
        result.add(new Pair<>(7, 4));
        result.add(new Pair<>(7, 5));
        return result;
    }

    public void setDirection(SnakesDirection direction) {
        this.direction = direction;
    }

    public Queue<Pair<Integer, Integer>> getSnake() {
        return snake;
    }


    public Pair<Integer, Integer> getSnakesHead() {
        return snakesHead;
    }

    public boolean hasSnakeDidWrongMove() {
        return snakeDidWrongMove;
    }
}
