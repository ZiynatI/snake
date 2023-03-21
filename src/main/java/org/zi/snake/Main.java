package org.zi.snake;

import org.zi.snake.entity.Field;
import org.zi.snake.ui.SwingRenderer;

public class Main {
    public static void main(String[] args) throws Exception {
        Field field = new Field((int) (Math.random() * 1000000));

        SwingRenderer ui = new SwingRenderer(field.getFieldSize(), field.getFieldSize());
        ui.setVisible(true);

        GameEngine gameEngine = new GameEngine(field, ui) {
            @Override
            protected void sleepForTest() throws InterruptedException {
                /*NOOP*/
            }
        };

        ui.setInputCallbackHandler(gameEngine);

        gameEngine.play();

        System.exit(0);
    }
}
