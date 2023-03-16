package org.zi.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeFrame extends JFrame {
    public static void main(String[] args) {
        new SnakeFrame();
        JFrame jFrame = new JFrame();

    }

    public SnakeFrame() {
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 640);
        this.setResizable(false);

//        this.getContentPane().setBackground(new Color(51, 153, 255));
        Button startButton = new Button("Start");
        startButton.setActionCommand("Start");
        startButton.addActionListener(new ButtonClickListener());
        this.add(startButton);
        this.add(new FieldComponent());
        this.setVisible(true);

    }

    private class FieldComponent extends JComponent {

        @Override
        public void paint(Graphics g) {
            super.paint(g);


            Graphics2D graphics2D = (Graphics2D) g;
            boolean green = true;
            for (int i = 0; i < 16; i++) {
                green = !green;
                for (int j = 0; j < 16; j++) {
                    Rectangle rectangle = new Rectangle(i*40, j*40, 40, 40);

                    graphics2D.setColor(green ? Color.green : Color.YELLOW);
                    green = !green;
                    graphics2D.draw(rectangle);
                }
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("OK")) {
            }
        }
    }
}
