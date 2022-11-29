package edu.ship.engr.presentation;

import edu.ship.engr.messages.Grow;
import edu.ship.engr.messages.InitializeSnake;
import edu.ship.engr.messages.Message;
import edu.ship.engr.peertopeer.PlayRunner;
import edu.ship.engr.presentation.gameobjects.Apple;
import edu.ship.engr.presentation.gameobjects.Rectangle;
import edu.ship.engr.presentation.gameobjects.Snake;

import javax.swing.*;
import java.awt.*;

public class HostSnakeGame extends SnakeGame {
    /**
     * Creates a new JPanel to contain the snake game
     *
     * @param window the JFrame window
     */
    public HostSnakeGame(GameFrame window) {
        super(window);
        this.apple = new Apple();
        this.isHost = true;

        startGame();
    }

    /**
     * Starts the game timer and instantiates a new snake
     */
    @Override
    public void startGame() {
        Timer timer = new Timer(DELAY, this);
        timer.start();

        snake = new Snake(75, 0, SPEED, new Color(18, 95, 227), new Color(12, 75, 152));
        InitializeSnake initializeSnake = new InitializeSnake(true, SnakeGame.clock.getUpdatedClock(), 100, 0, SPEED, "18,95,227", "12,75,152");
        PlayRunner.messageAccumulator.queueMessage(new Message<>(initializeSnake));
        apple.spawnApple(true);
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
                apple.spawnApple(true);

                Grow growSnake = new Grow(true, SnakeGame.clock.getUpdatedClock());
                PlayRunner.messageAccumulator.queueMessage(new Message<>(growSnake));
            }
        }
    }
}
