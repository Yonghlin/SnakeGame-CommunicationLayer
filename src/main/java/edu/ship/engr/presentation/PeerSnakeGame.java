package edu.ship.engr.presentation;

import edu.ship.engr.communication.handlers.AppleHandler;
import edu.ship.engr.messages.AppleLocation;
import edu.ship.engr.presentation.gameobjects.Apple;
import edu.ship.engr.presentation.gameobjects.Rectangle;
import edu.ship.engr.presentation.gameobjects.Snake;

import javax.swing.*;
import java.awt.*;

public class PeerSnakeGame extends SnakeGame {

    /**
     * Creates a new JPanel to contain the snake game
     *
     * @param window the JFrame window
     */
    public PeerSnakeGame(GameFrame window) {
        super(window);
        this.apple = new Apple();
        startGame();
    }

    /**
     * Starts the game timer and instantiates a new snake
     */
    @Override
    public void startGame() {
        Timer timer = new Timer(175, this);
        timer.start();

        snake = new Snake(75, 25, 25, new Color(145, 67, 67), new Color(150, 17, 23));
        //TODO: Tell host the peer connected
        

    }

    /**
     * Checks for fruit
     * @param snakeHead the head of the snake
     */
    @Override
    public void checkAppleCollision(edu.ship.engr.presentation.gameobjects.Rectangle snakeHead) {
        if (apple.getInPlay()) {
            if (snakeHead.intersects(new Rectangle(apple.getXPosition(), apple.getYPosition()))) {
                apple.despawnApple();
                snake.grow();

                //TODO: Tell host the apple should be gone
            }
        }
    }
}