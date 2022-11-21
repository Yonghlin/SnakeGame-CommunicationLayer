package edu.ship.engr.presentation;

import javax.swing.*;

public class GameFrame extends JFrame {

    /**
     * Creates a new JFrame and Snake game
     */
    public GameFrame(String mode) {
        this.add(new SnakeGame(this,  mode));
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
    }
}
