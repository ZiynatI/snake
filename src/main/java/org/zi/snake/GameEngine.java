package org.zi.snake;

import java.util.Scanner;

import org.zi.snake.Field;
import org.zi.snake.Snake;

public class GameEngine {

    public static void play() throws InterruptedException {
        boolean gameOver = false;
        Field field = new Field();
        Scanner input = new Scanner(System.in);
        Snake snake = new Snake();
        System.out.println(field.toString(snake));
        while (!gameOver) {
            int sleepingTime = 500;
            Thread.sleep(sleepingTime);
            directSnake(input, snake);
            snake.move(field);
            if (snake.getSnakesHead().equals(field.getApple())) {
                field.setAnApple(false);
                field.generateApple(snake.getSnakesLocation());
                snake.setScore();
            } else {
                snake.getSnakesLocation().remove();
            }
            System.out.println(field.toString(snake));
            if (snake.didWrongMove()) {
                gameOver = true;
            }
        }
        System.out.println("Game over\nScore:" + snake.getScore());
    }

    public static void directSnake(Scanner input, Snake snake) {
        String nextDir = "";
        do {
            nextDir = input.next();
            switch (nextDir) {
                case "W": {
                    snake.setDirection(Direction.UP);
                    break;
                }
                case "A": {
                    snake.setDirection(Direction.LEFT);
                    break;
                }
                case "S": {
                    snake.setDirection(Direction.DOWN);
                    break;
                }
                case "D": {
                    snake.setDirection(Direction.RIGHT);
                    break;
                }
            }
        } while (!(nextDir.equals("W") || nextDir.equals("A") || nextDir.equals("S") || nextDir.equals("D")));
    }

}
