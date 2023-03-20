package org.zi.snake;


import org.zi.snake.entity.Direction;

public interface InputCallbackHandler {
    Direction getCurrentDirection();

    void setNextDirection(Direction direction);

    void stop();

}
