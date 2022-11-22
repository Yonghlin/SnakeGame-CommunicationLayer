package edu.ship.engr.presentation;

import edu.ship.engr.presentation.gameobjects.Rectangle;

public interface SnakeGameInterface {
    public void startGame();
    public void checkAppleCollision(Rectangle snakeHead);
}
