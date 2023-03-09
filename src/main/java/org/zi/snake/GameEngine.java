package org.zi.snake;

import java.util.Scanner;

public class GameEngine {
    public void play() throws InterruptedException {
        int score = 0;
        boolean gameOver = false;
        Field field = new Field();
        Scanner input = new Scanner(System.in);
        System.out.println(field.toString());
        while (!gameOver) {
            int sleepingTime = 500;
            Thread.sleep(sleepingTime);
            directSnake(input, field);
            field.moveSnake();
            if (field.getSnakesHead().equals(field.getApple())) {
                field.generateApple();
                score++;
            } else {
                field.getSnake().remove();
            }
            System.out.println(field.toString());
            if (field.hasSnakeDidWrongMove()) {
                gameOver = true;
            }
        }
        System.out.println("Game over\nScore:" + score);
    }

    public static void directSnake(Scanner input, Field field) {
        String nextDir = "";
        do {
            nextDir = input.next();
            switch (nextDir) {
                case "W": {
                    field.setDirection(SnakesDirection.UP);
                    break;
                }
                case "A": {
                    field.setDirection(SnakesDirection.LEFT);
                    break;
                }
                case "S": {
                    field.setDirection(SnakesDirection.DOWN);
                    break;
                }
                case "D": {
                    field.setDirection(SnakesDirection.RIGHT);
                    break;
                }
            }
        } while (!(nextDir.equals("W") || nextDir.equals("A") || nextDir.equals("S") || nextDir.equals("D")));
    }

}
