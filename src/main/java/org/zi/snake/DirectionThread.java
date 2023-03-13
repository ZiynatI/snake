package org.zi.snake;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DirectionThread extends Thread {
    private Scanner input = new Scanner(System.in);
    private final Map<String, Direction> inputToDirMap = turnInputToDir();
    private InputCallbackHandler inputCallbackHandler;


    public DirectionThread(InputCallbackHandler inputCallbackHandler) {
        this.inputCallbackHandler = inputCallbackHandler;
    }

    @Override
    public void run() {
        while (true) {
            String nextDir = input.next().toUpperCase();
            Direction nextProbDir = inputToDirMap.get(nextDir);
            if (inputCallbackHandler.getCurrentDirection().getAllowedDirections().contains(nextProbDir)) {
                inputCallbackHandler.setNextDirection(nextProbDir);
            }
        }
    }

    private Map<String, Direction> turnInputToDir() {
        Map<String, Direction> inputToDirMap = new HashMap<>();
        inputToDirMap.put("W", Direction.UP);
        inputToDirMap.put("A", Direction.LEFT);
        inputToDirMap.put("S", Direction.DOWN);
        inputToDirMap.put("D", Direction.RIGHT);
        return inputToDirMap;

    }
}
