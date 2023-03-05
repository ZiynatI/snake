import java.util.Scanner;

public class Snake {
    private int length;
    Direction direction;
    Scanner input;

    public Snake(Scanner input) {
        this.length = 3;
        this.direction = Direction.RIGHT;
        this.input = input;
    }

    public Direction move() {
        boolean timeToMove = false;
        long then = System.currentTimeMillis();
        while (!timeToMove) {
            if (this.input.hasNext()) {
                String nextDir = this.input.next();
                switch (nextDir) {
                    case "R":
                        return Direction.RIGHT;
                    case "L":
                        return Direction.LEFT;
                    case "U":
                        return Direction.UP;
                    case "D":
                        return Direction.DOWN;
                }
            }
            long now = System.currentTimeMillis();
            if (now - then >= 1000) {
                timeToMove = true;
            }
        }
        return Direction.DEFAULT;
    }

    public enum Direction {

        DEFAULT("0"), LEFT("L"), RIGHT("R"), UP("U"), DOWN("D");
        String directionValue;

        Direction(String directionValue) {

        }
    }
}
