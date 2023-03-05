import java.util.*;

public class Snake {
    Queue<Pair<Integer, Integer>> snakesLocation;
    Direction direction;
    Scanner input;
    int score;
    Pair<Integer, Integer> snakesHead = new Pair<>(7, 5);

    public Snake(Scanner input) {
        this.snakesLocation = startingSnake();
        this.direction = Direction.RIGHT;
        this.input = input;
        this.score = 0;
    }

    public Direction move(Field field) {
        if (this.input.hasNext()) {
            String nextDir = this.input.next();
            switch (nextDir) {
                case "R": {
                    snakesHead = new Pair<>(snakesHead.left, snakesHead.right + 1);
                    this.direction = Direction.RIGHT;
                }
                case "L": {
                    snakesHead = new Pair<>(snakesHead.left, snakesHead.right);
                    this.direction = Direction.LEFT;
                }
                case "U": {
                    snakesHead = new Pair<>(snakesHead.left + 1, snakesHead.right);
                    this.direction = Direction.UP;
                }
                case "D": {
                    snakesHead = new Pair<>(snakesHead.left - 1, snakesHead.right);
                    this.direction = Direction.DOWN;
                }
            }
            snakesLocation.offer(snakesHead);
            if (field.apple.equals(snakesHead)) {
                field.hasAnApple = false;
                this.score++;
            } else {
                snakesLocation.remove();
            }
        }

        return this.direction;
    }

    public static Queue<Pair<Integer, Integer>> startingSnake() {
        Queue<Pair<Integer, Integer>> result = new LinkedList<>();
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
