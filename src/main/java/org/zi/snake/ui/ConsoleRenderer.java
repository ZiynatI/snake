package org.zi.snake.ui;

import org.zi.snake.entity.Field;
import org.zi.snake.entity.Pair;

public class ConsoleRenderer implements Renderer {
    @Override
    public void renderField(Field field) {
        System.out.println(printField(field));
    }

    @Override
    public void renderGameOver(int score) {
        System.out.println("Game over\nScore:" + score);
    }

    private String printField(Field field) {
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
