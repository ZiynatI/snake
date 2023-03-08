package org.zi.snake;

import java.util.Queue;

public class Field {
    private int[][] field;
    private Pair<Integer, Integer> apple;
    private boolean hasAnApple;

    public Field() {
        this.field = new int[16][16];
        this.apple = new Pair<>(7, 11);
        hasAnApple = true;
    }


    public void generateApple(Queue<Pair<Integer, Integer>> snakesLocation) {
        if (!hasAnApple) {
            Pair<Integer, Integer> applesLocation;
            do {
                applesLocation = new Pair<>((int) (Math.random() * 17), (int) (Math.random() * 17));
            } while (snakesLocation.contains(applesLocation));
            this.apple = applesLocation;
            this.hasAnApple = true;
        }
    }

    public String toString(Snake snake) {
        StringBuilder sb = new StringBuilder();
        sb.append("__________________\n");
        for (int i = 0; i < field.length; i++) {
            sb.append("|");
            for (int j = 0; j < field[i].length; j++) {
                if (new Pair<>(i, j).equals(apple)) {
                    sb.append("A");
                } else if (snake.getSnakesLocation().contains(new Pair<>(i, j))) {
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

    public int[][] getField() {
        return field;
    }

    public Pair<Integer, Integer> getApple() {
        return apple;
    }

    public boolean hasAnApple() {
        return hasAnApple;
    }

    public void setAnApple(boolean hasAnApple) {
        this.hasAnApple = hasAnApple;
    }
}
