package org.zi.snake;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.zi.snake.entity.Direction;
import org.zi.snake.entity.Field;
import org.zi.snake.entity.Pair;
import org.zi.snake.ui.ConsoleRenderer;

import java.util.Arrays;

public class GameEngineTest {
    boolean testing = false;

    @Test
    public void engineTest() throws Exception {
        Field field = new Field(111111);
        ConsoleRenderer cr = new ConsoleRenderer();

        final GameEngine engine = new GameEngine(field, cr) {
            @Override
            protected void sleepForTest() {
                assertEquals(Arrays.asList(new Pair<>(7, 4), new Pair<>(7, 5), new Pair<>(7, 6)), field.getSnake());
            }
        };

        engine.play();


        assertEquals(Arrays.asList(new Pair<>(7, 5), new Pair<>(7, 6), new Pair<>(7, 7)), field.getSnake());

        engine.setNextDirection(Direction.DOWN);
        assertEquals(Arrays.asList(new Pair<>(7, 6), new Pair<>(7, 7), new Pair<>(8, 7)), field.getSnake());


    }
}
