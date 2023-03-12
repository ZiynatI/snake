package org.zi.snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum SnakesDirection {
    LEFT, RIGHT, UP, DOWN;

    private List<SnakesDirection> allowedDirections = null;

    public List<SnakesDirection> getAllowedDirections() {
        if (allowedDirections == null) {
            List<SnakesDirection> tmp = new ArrayList<>(Arrays.asList(SnakesDirection.values()));
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
}
