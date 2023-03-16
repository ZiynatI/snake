package org.zi.snake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    private Field field = new Field();

    @Test
    void appleShouldBeHereAtBeginning() {
        Pair<Integer, Integer> pair = new Pair<>(7, 11);
        Pair<Integer, Integer> actualPair = field.getApple();
        Assertions.assertEquals(pair.getLeft(), actualPair.getLeft());
        Assertions.assertEquals(pair.getRight(), pair.getRight());
    }

    @Test
    void shouldMakeRightMoves() {
        field.moveSnakeToEatApple();
        Assertions.assertTrue(field.getSnake().contains(new Pair<>(7, 6)));
        Assertions.assertFalse(field.getSnake().contains(new Pair<>(7, 3)));
    }

    @Test
    void hasSnakeDidWrongMoveTest() {
        field.setDirection(Direction.UP);
        field.moveSnakeToEatApple();
        field.setDirection(Direction.LEFT);
        field.moveSnakeToEatApple();
        field.setDirection(Direction.DOWN);
        field.moveSnakeToEatApple();
        Assertions.assertFalse(field.hasSnakeDidWrongMove());
    }
}