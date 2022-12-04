package edu.ship.engr.presentation.gameobjects;

import edu.ship.engr.messages.AppleLocation;
import edu.ship.engr.messages.Message;
import edu.ship.engr.peertopeer.PlayRunner;
import edu.ship.engr.presentation.SnakeGame;

import java.util.Random;

public class Apple {
    private int xPosition;
    private int yPosition;
    private boolean inPlay;

    /**
     * Creates a new apple object
     */
    public Apple() {
        this.xPosition = 0;
        this.yPosition = 0;
    }

    /**
     * Create an apple at a random position and sets it to be in play
     * @param isHost true if the host called this method
     */
    public void spawnApple(boolean isHost) {
        inPlay = true;

        xPosition = new Random().nextInt(SnakeGame.SCREEN_WIDTH/ SnakeGame.UNIT_SIZE) * SnakeGame.UNIT_SIZE;
        yPosition = new Random().nextInt(SnakeGame.SCREEN_HEIGHT/ SnakeGame.UNIT_SIZE) * SnakeGame.UNIT_SIZE;

        AppleLocation appleLocation = new AppleLocation(isHost, xPosition, yPosition);
        PlayRunner.messageAccumulator.queueMessage(new Message<>(appleLocation));
    }

    /**
     * Spawns an apple at a specific location
     * @param isHost true if the host called this method
     * @param xPos the x position to spawn at
     * @param yPos the y position to spawn at
     */
    public void spawnAppleAtLocation(boolean isHost, int xPos, int yPos) {
        inPlay = true;

        xPosition = xPos;
        yPosition = yPos;

        AppleLocation appleLocation = new AppleLocation(isHost, xPosition, yPosition);
        PlayRunner.messageAccumulator.queueMessage(new Message<>(appleLocation));
    }

    /**
     * Removes an apple from being in play
     */
    public void despawnApple() {
        inPlay = false;

        xPosition = 0;
        yPosition = 0;
    }

    /**
     * Sets the apples x position
     * @param newXPosition the new x position
     */
    public void setXPosition(int newXPosition) {
        xPosition = newXPosition;
    }

    /**
     * Sets the apples y position
     * @param newYPosition the new y position
     */
    public void setYPosition(int newYPosition) {
        yPosition = newYPosition;
    }

    /**
     * sets the apples state in the game
     * @param isInPlay should the apple be currently in play
     */
    public void setInPlay(boolean isInPlay) {
        inPlay = isInPlay;
    }

    /**
     * @return true if the apple is eatable in game
     */
    public boolean getInPlay() {
        return inPlay;
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