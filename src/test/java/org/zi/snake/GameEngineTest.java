package org.zi.snake;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.zi.snake.entity.Direction;
import org.zi.snake.entity.Field;
import org.zi.snake.entity.Pair;
import org.zi.snake.ui.ConsoleRenderer;
import org.zi.snake.ui.Renderer;

import java.util.Arrays;

public class GameEngineTest {

    @Test
    public void engineTest() throws Exception {
        Field field = new Field(111111);
        Renderer rdr = new Renderer() {
            @Override
            public void renderField(Field field) {
                /*NOOP*/
            }

            @Override
            public void renderGameOver(int score) {
                /*NOOP*/
            }
        };

        final GameEngine engine = new GameEngine(field, rdr);

        engine.playRound();
        assertEquals(Arrays.asList(new Pair<>(7, 4), new Pair<>(7, 5), new Pair<>(7, 6)), field.getSnake());

        engine.playRound();
        assertEquals(Arrays.asList(new Pair<>(7, 5), new Pair<>(7, 6), new Pair<>(7, 7)), field.getSnake());

        engine.setNextDirection(Direction.DOWN);
        engine.playRound();
        assertEquals(Arrays.asList(new Pair<>(7, 6), new Pair<>(7, 7), new Pair<>(8, 7)), field.getSnake());


    }
}
