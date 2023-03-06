package org.zi.snake;

public class Field {
    int[][] field;
    Pair<Integer, Integer> apple;
    boolean hasAnApple;

    Field() {
        this.field = new int[16][16];
        this.apple = new Pair<>(7, 11);
        hasAnApple = true;
    }


    public void generateApple() {
        if (!hasAnApple) {
            this.apple = new Pair<>((int) (Math.random() * 17), (int) (Math.random() * 17));
            this.hasAnApple = true;
        }
    }

    public String toString(Snake snake) {
        StringBuilder sb = new StringBuilder();
        sb.append("__________________\n");
        for (int i = 0; i < field.length; i++) {
            sb.append("|");
            for (int j = 0; j < field[i].length; j++) {
                if(new Pair<>(i,j).equals(apple)){
                    sb.append("A");
                }
               else if (snake.snakesLocation.contains(new Pair<>(i, j))) {
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
