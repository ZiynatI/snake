package org.zi.snake.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    private List<Direction> allowedDirections = null;

    private List<Direction> getAllowedDirections() {
        if (allowedDirections == null) {
            List<Direction> tmp = new ArrayList<>(Arrays.asList(Direction.values()));
            switch (this) {
                case UP:
                    tmp.remove(DOWN);
                    break;
                case DOWN:
                    tmp.remove(UP);
                    break;
                case LEFT:
                    tmp.remove(RIGHT);
                    break;
                case RIGHT:
                    tmp.remove(LEFT);
                    break;
            }
            allowedDirections = tmp;
        }
        return allowedDirections;
    }

    public boolean isDirectionAllowed(Direction direction) {
        return getAllowedDirections().contains(direction);
    }
}
