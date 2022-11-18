package edu.ship.engr.presentation;

import java.util.Random;

public class Apple {
    private int xPosition;
    private int yPosition;

    /**
     * Creates a new apple
     */
    public Apple() {
        this.xPosition = new Random().nextInt(SnakeGame.SCREEN_WIDTH/ SnakeGame.UNIT_SIZE) * SnakeGame.UNIT_SIZE;
        this.yPosition = new Random().nextInt(SnakeGame.SCREEN_HEIGHT/ SnakeGame.UNIT_SIZE) * SnakeGame.UNIT_SIZE;
    }

    /**
     * @return the x position of the apple
     */
    public int getXPosition() {
        return xPosition;
    }

    /**
     * @return the y position of the apple
     */
    public int getYPosition() {
        return yPosition;
    }
}