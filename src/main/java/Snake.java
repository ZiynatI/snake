import java.util.*;

public class Snake {
    Deque<Pair<Integer, Integer>> snakesLocation;
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

                    this.direction = Direction.RIGHT;
                    snakesLocation.offer(new Pair<>(snakesHead.left, snakesHead.right + 1));
                }
                case "L": {

                    this.direction = Direction.LEFT;
                    snakesLocation.offer(new Pair<>(snakesHead.left, snakesHead.right - 1));
                }
                case "U": {

                    this.direction = Direction.UP;
                    snakesLocation.offer(new Pair<>(snakesHead.left - 1, snakesHead.right));
                }
                case "D": {

                    this.direction = Direction.DOWN;
                    snakesLocation.offer(new Pair<>(snakesHead.left + 1, snakesHead.right));
                }
            }
        }
        snakesHead = snakesLocation.getFirst();
        if (field.apple.equals(snakesHead)) {
            field.hasAnApple = false;
            this.score++;
        } else {
            snakesLocation.remove();
        }


        return this.direction;
    }

    public static Deque<Pair<Integer, Integer>> startingSnake() {
        Deque<Pair<Integer, Integer>> result = new LinkedList<>();
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
