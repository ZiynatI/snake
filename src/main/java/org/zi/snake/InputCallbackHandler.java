package org.zi.snake;


public interface InputCallbackHandler {
    Direction getCurrentDirection();

    void setNextDirection(Direction direction);

    void hasChagedDirThisTime();

    boolean ifSetDirThisTime();
}
