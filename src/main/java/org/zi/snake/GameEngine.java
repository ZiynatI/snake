package org.zi.snake;

import org.zi.snake.entity.Direction;
import org.zi.snake.entity.Field;
import org.zi.snake.ui.Renderer;

public abstract class GameEngine implements InputCallbackHandler {

    private final int SLEEPING_TIME = 200;

    private int score = 0;
    private boolean stopped = false;

    private final Field field;
    private final Renderer renderer;

    private Direction directionForDirectionThread = Direction.RIGHT;

    public GameEngine(Field field, Renderer renderer) {
        this.field = field;
        this.renderer = renderer;
    }

    public void play() throws InterruptedException {
        boolean gameOver = false;
        renderer.renderField(field);
        while (!gameOver && !stopped) {
            long startTime = System.currentTimeMillis();
            gameOver = playRound();
            long endTime = System.currentTimeMillis();
            Thread.sleep(Math.max(0L, SLEEPING_TIME - (endTime - startTime)));
        }
        if (!stopped) {
            renderer.renderGameOver(score);
        }
    }

    protected boolean playRound() {
        field.setDirection(directionForDirectionThread);
        boolean ateApple = field.moveSnakeToEatApple();
        if (ateApple) {
            score++;
        }
        renderer.renderField(field);
        return field.hasSnakeDidWrongMove();
    }

    @Override
    public Direction getCurrentDirection() {
        return field.getDirection();
    }

    @Override
    public void setNextDirection(Direction direction) {
        directionForDirectionThread = direction;
    }

    @Override
    public void stop() {
        stopped = true;
    }

    protected abstract void sleep(long ms) throws InterruptedException;
}
