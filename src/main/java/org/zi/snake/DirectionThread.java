package org.zi.snake;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DirectionThread extends Thread {
    private Scanner input = new Scanner(System.in);
    private Map<String, SnakesDirection> inputToDirMap = inputToDir();
    private Field field;

    DirectionThread(Field field) {
        this.field = field;
    }

    @Override
    public void run() {
        field.setDirection(getNextDirection(input, field));
    }


    private SnakesDirection getNextDirection(Scanner input, Field field) {
        do {
            String nextDir = input.next().toUpperCase();
            SnakesDirection nextProbDir = inputToDirMap.get(nextDir);
            if (field.getDirection().getAllowedDirections().contains(nextProbDir)) {
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
