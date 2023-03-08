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
            Thread.sleep(500);
            String nextDir = "";
            while (!(nextDir.equals("W") || nextDir.equals("A") || nextDir.equals("S") || nextDir.equals("D"))) {
                nextDir = input.next();
            }
            snake.move(field, nextDir);
            System.out.println(field.toString(snake));
            if (snake.snakeCameWrong) {
                gameOver = true;
            }
        }
        System.out.println("Game over\nScore:" + snake.score);
    }

}
