package org.zi.snake;

import org.zi.snake.entity.Field;
import org.zi.snake.ui.SwingRenderer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Field field = new Field();

        SwingRenderer ui = new SwingRenderer(field.getFieldSize(), field.getFieldSize());
        ui.setVisible(true);

        GameEngine gameEngine = new GameEngine(field, ui) {
            @Override
            protected void sleep(long ms) throws InterruptedException {
                Thread.sleep(ms);
            }
        };

        ui.setInputCallbackHandler(gameEngine);

        gameEngine.play();

        System.exit(0);
    }
}
