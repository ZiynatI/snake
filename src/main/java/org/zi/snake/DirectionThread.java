package org.zi.snake;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DirectionThread extends Thread {
    private Scanner input = new Scanner(System.in);
    private Map<String, SnakesDirection> inputToDirMap = inputToDir();
    private GameEngine gameEngine;

    public DirectionThread(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {
        while (true) {
            gameEngine.getField().setDirection(getNextDirection(input));
        }
    }


    private SnakesDirection getNextDirection(Scanner input) {
        do {
            String nextDir = input.next().toUpperCase();
            SnakesDirection nextProbDir = inputToDirMap.get(nextDir);
            if (gameEngine.getField().getDirection().getAllowedDirections().contains(nextProbDir)) {
                return nextProbDir;
            }
        } while (true);
    }

    private Map<String, SnakesDirection> inputToDir() {
        Map<String, SnakesDirection> inputToDirMap = new HashMap<>();
        inputToDirMap.put("W", SnakesDirection.UP);
        inputToDirMap.put("A", SnakesDirection.LEFT);
        inputToDirMap.put("S", SnakesDirection.DOWN);
        inputToDirMap.put("D", SnakesDirection.RIGHT);
        return inputToDirMap;

    }
}
