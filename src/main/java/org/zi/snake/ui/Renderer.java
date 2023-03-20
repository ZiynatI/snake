package org.zi.snake.ui;

import org.zi.snake.entity.Field;

public interface Renderer {
    void renderField(Field field);

    void renderGameOver(int score);
}
