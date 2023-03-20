package org.zi.snake.ui;

import org.zi.snake.entity.Direction;
import org.zi.snake.entity.Field;
import org.zi.snake.InputCallbackHandler;
import org.zi.snake.entity.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingRenderer extends JFrame implements Renderer {
    private InputCallbackHandler inputCallbackHandler = null;
    private final JComponent[][] cells;

    public SwingRenderer(int rows, int cols) {
        this.cells = new JComponent[rows][cols];

        this.setTitle("Snake");
        this.setSize(rows * 40, cols * 40);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                inputCallbackHandler.stop();
            }
        });

        {
            JPanel panel = new JPanel();
            this.add(panel);
            panel.setBackground(Color.black);
            panel.setLayout(new GridLayout(rows, cols, 1, 1));
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    JPanel btn = new JPanel();
                    panel.add(btn);
                    this.cells[i][j] = btn;
                }
            }
        }

        Toolkit.getDefaultToolkit().addAWTEventListener(new MovementAwtListener(), AWTEvent.KEY_EVENT_MASK);
    }

    public void setInputCallbackHandler(InputCallbackHandler inputCallbackHandler) {
        this.inputCallbackHandler = inputCallbackHandler;
    }

    @Override
    public void renderField(Field field) {
        for (JComponent[] row : cells) {
            for (JComponent cell : row) {
                cell.setBackground(Color.WHITE);
            }
        }

        {
            Pair<Integer, Integer> appleCoords = field.getApple();
            cells[appleCoords.getLeft()][appleCoords.getRight()].setBackground(Color.red);
        }

        for (Pair<Integer, Integer> snakePartCoords : field.getSnake()) {
            cells[snakePartCoords.getLeft()][snakePartCoords.getRight()].setBackground(Color.gray);
        }

        {
            Pair<Integer, Integer> headCoords = field.getSnakesHead();
            cells[headCoords.getLeft()][headCoords.getRight()].setBackground(Color.darkGray);
        }

        this.getContentPane().invalidate();
        this.getContentPane().repaint();
    }

    @Override
    public void renderGameOver(int score) {
        JOptionPane.showMessageDialog(null, "Game over! Your score: " + score,
                "Snake", JOptionPane.INFORMATION_MESSAGE);
    }


    private class MovementAwtListener implements AWTEventListener {
        @Override
        public void eventDispatched(AWTEvent awtEvent) {
            Direction direction = getDirectionFromKey((KeyEvent) awtEvent);
            if (direction != null && inputCallbackHandler.getCurrentDirection().isDirectionAllowed(direction)) {
                inputCallbackHandler.setNextDirection(direction);
            }
        }

        private Direction getDirectionFromKey(KeyEvent keyEvent) {
            if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        return Direction.UP;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        return Direction.DOWN;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        return Direction.LEFT;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        return Direction.RIGHT;
                    default:
                        // NOOP
                }
            }
            return null;
        }
    }
}
