package edu.ship.engr.presentation;

import javax.swing.*;

public class GameFrame extends JFrame {

    /**
     * Creates a new JFrame and Snake game
     */
    public GameFrame(boolean isHost) {
        if(isHost) {
            this.add(new HostSnakeGame(this));
        } else {
            this.add(new PeerSnakeGame(this));
        }

        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
    }
}
