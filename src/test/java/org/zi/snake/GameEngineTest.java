package org.zi.snake;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GameEngineTest {

    @Test
    public void engineTest() throws Exception {
        final Object turnLock = new Object();
        final Object waitLock = new Object();

        final Exception[] failure = {null};
        Field field = new Field();

        final GameEngine engine = new GameEngine(field) {
            @Override
            protected void sleep(long ms) throws InterruptedException {
                synchronized (turnLock) {
                    turnLock.notify();
                }
                synchronized (waitLock) {
                    waitLock.wait();
                }
            }
        };

        Thread engineThread = new Thread(() -> {
            try {
                engine.play();
            } catch (Exception ex) {
                failure[0] = ex;
            }
        });
        engineThread.setDaemon(true);
        engineThread.start();

        synchronized (turnLock) {
            turnLock.wait();
        }
        assertEquals(Arrays.asList(new Pair<>(7, 4), new Pair<>(7, 5), new Pair<>(7, 6)), field.getSnake().stream().toList());

        unpause(waitLock, turnLock);
        assertEquals(Arrays.asList(new Pair<>(7, 5), new Pair<>(7, 6), new Pair<>(7, 7)), field.getSnake().stream().toList());

        engine.setNextDirection(Direction.DOWN);
        unpause(waitLock, turnLock);
        assertEquals(Arrays.asList(new Pair<>(7, 6), new Pair<>(7, 7), new Pair<>(8, 7)), field.getSnake().stream().toList());

        assertNull(failure[0]);
    }

    private void unpause(Object waitLock, Object turnLock) throws InterruptedException {
        synchronized (waitLock) {
            waitLock.notify();
        }
        synchronized (turnLock) {
            turnLock.wait();
        }
    }
}
