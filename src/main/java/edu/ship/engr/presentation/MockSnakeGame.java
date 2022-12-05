package edu.ship.engr.presentation;

import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;
import edu.ship.engr.presentation.gameobjects.Apple;
import edu.ship.engr.presentation.gameobjects.Rectangle;
import edu.ship.engr.presentation.gameobjects.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

public class MockSnakeGame extends SnakeGame {
    private boolean isHost;

    /**
     * Creates a new JPanel to contain the snake game
     *
     * @param window the JFrame window
     */
    public MockSnakeGame(GameFrame window, boolean isHost) {
        super(window);
        this.apple = new Apple();
        this.isHost = isHost;

        startGame();
    }

    @Override
    public void startGame() {
        if (isHost) {
            snake = new Snake(400, 275, SPEED, "right", new Color(18, 95, 227), new Color(12, 75, 152));
        } else {
            snake = new Snake(475, 275, SPEED, "left", new Color(145, 67, 67), new Color(150, 17, 23));
        }
    }

    @Override
    public void checkAppleCollision(Rectangle snakeHead) {

    }

    @Override
    public void endGame() {
        GameOver = true;
    }
}
