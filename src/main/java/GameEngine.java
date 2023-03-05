import java.util.Scanner;

public class GameEngine {


    public static void play() throws InterruptedException {
        boolean gameOver = false;
        Field field = new Field();
        Scanner input = new Scanner(System.in);
        Snake snake = new Snake(input);
        System.out.println(field.toString(snake));
        while (!gameOver) {
            Thread.sleep(1000);
            snake.move(field);
            System.out.println(field.toString(snake));
        }
    }
}
