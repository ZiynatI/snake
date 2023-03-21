package org.zi.snake;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.zi.snake.entity.Direction;
import org.zi.snake.entity.Field;
import org.zi.snake.entity.Pair;

import java.util.Arrays;

class FieldTest {
    @Test
    void appleShouldBeHereAtBeginning() {
        Field field = new Field(111111);
        Pair<Integer, Integer> pair = new Pair<>(7, 11);
        assertEquals(pair, field.getApple());
    }

    @Test
    void shouldMakeRightMoves() {
        Field field = new Field(111111);
        field.moveSnakeToEatApple();
        assertEquals(field.getSnake(), Arrays.asList(new Pair<>(7, 4), new Pair<>(7, 5), new Pair<>(7, 6)));
        field.setDirection(Direction.DOWN);
        field.moveSnakeToEatApple();
        assertEquals(field.getSnake(), Arrays.asList(new Pair<>(7, 5), new Pair<>(7, 6), new Pair<>(8, 6)));
        field.setDirection(Direction.LEFT);
        field.moveSnakeToEatApple();
        assertEquals(field.getSnake(), Arrays.asList(new Pair<>(7, 6), new Pair<>(8, 6), new Pair<>(8, 5)));
        field.setDirection(Direction.UP);
        field.moveSnakeToEatApple();
        assertEquals(field.getSnake(), Arrays.asList(new Pair<>(8, 6), new Pair<>(8, 5), new Pair<>(7, 5)));
        field.setDirection(Direction.RIGHT);
        field.moveSnakeToEatApple();
        assertEquals(field.getSnake(), Arrays.asList(new Pair<>(8, 5), new Pair<>(7, 5), new Pair<>(7, 6)));
    }

    @Test
    void setDirectionTest() {
        Field field = new Field(111111);
        assertEquals(field.getDirection(), Direction.RIGHT);
    }

    @Test
    void getSnakesHeadTest() {
        Field field = new Field(111111);
        assertEquals(field.getSnakesHead(), new Pair<>(7, 5));
    }

    @Test
    void hasSnakeDidWrongMove() {
        Field field = new Field(111111);
        int count = field.getFieldSize() - field.getSnakesHead().getRight();
        while (count > 0) {
            field.moveSnakeToEatApple();
            count--;
        }
        assertEquals(field.hasSnakeDidWrongMove(), true);
    }

    @Test
    void getFieldSize() {
        Field field = new Field(111111);
        assertEquals(16, field.getFieldSize());
    }

    @Test
    void getDirection() {
        Field field = new Field(111111);
        assertEquals(field.getDirection(), Direction.RIGHT);
        field.setDirection(Direction.UP);
        assertEquals(field.getDirection(), Direction.UP);
        field.setDirection(Direction.LEFT);
        assertEquals(field.getDirection(), Direction.LEFT);
        field.setDirection(Direction.DOWN);
        assertEquals(field.getDirection(), Direction.DOWN);
    }

    @Test
    void generateAppleTest() {
        Field field = new Field(111111);
        int count = field.getApple().getRight() - field.getSnakesHead().getRight();
        while (count > 0) {
            field.moveSnakeToEatApple();
            count--;
        }
        assertEquals(field.getApple(), new Pair<>(4, 11));
    }
}
