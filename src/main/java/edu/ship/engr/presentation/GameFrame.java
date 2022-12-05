package edu.ship.engr.presentation;

import javax.swing.*;

public class GameFrame extends JFrame {
    public static SnakeGame hostSnakeGame; //static variables that contain the game
    public static SnakeGame peerSnakeGame;

    /**
     * Creates a new JFrame and Snake game
     */
    public GameFrame(boolean isHost) {
        if(isHost) {
            hostSnakeGame = new HostSnakeGame(this);

            this.setTitle("[HOST] Snake");
            this.add(hostSnakeGame);  //adds the panel to the JFrame
        } else {
            peerSnakeGame = new PeerSnakeGame(this);

            this.setTitle("[PEER] Snake");
            this.add(peerSnakeGame);
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.pack();
    }

    public GameFrame() {
        hostSnakeGame = new MockSnakeGame(this, true);
        peerSnakeGame = new MockSnakeGame(this, false);
    }
}
