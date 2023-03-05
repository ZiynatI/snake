import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Snake {
    List<Pair<Integer, Integer>> snakesLocation = startingSnake();
    Direction direction;
    Scanner input;
    int score;
    Pair<Integer, Integer> snakesHead = new Pair<>(7, 3);

    public Snake(Scanner input) {
        this.snakesLocation = startingSnake();
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

    public static ArrayList<Pair<Integer, Integer>> startingSnake() {
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        result.add(new Pair<>(7, 3));
        result.add(new Pair<>(7, 4));
        result.add(new Pair<>(7, 5));
        return result;
    }

    public enum Direction {

        DEFAULT("0"), LEFT("L"), RIGHT("R"), UP("U"), DOWN("D");
        String directionValue;

        Direction(String directionValue) {

        }
    }
}
