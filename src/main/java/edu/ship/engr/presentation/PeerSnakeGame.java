package edu.ship.engr.presentation;

import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.InitializeSnake;
import edu.ship.engr.peertopeer.PlayRunner;
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

        snake = new Snake(75, 25, SPEED, new Color(145, 67, 67), new Color(150, 17, 23));

        InitializeSnake initializeSnake = new InitializeSnake(false, 75, 25, SPEED, "145,67,67", "150,17,23");
        PlayRunner.messageAccumulator.queueMessage(new Message<>(initializeSnake));
    }

    /**
     * Checks for fruit
     * @param snakeHead the head of the snake
     */
    @Override
    public void checkAppleCollision(Rectangle snakeHead) {
        if (apple.getInPlay()) {
            if (snakeHead.intersects(new Rectangle(apple.getXPosition(), apple.getYPosition()))) {
                apple.despawnApple();
                snake.grow();
                apple.spawnApple(false);
            }
        }
    }
}
