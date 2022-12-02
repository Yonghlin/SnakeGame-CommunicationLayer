package edu.ship.engr.presentation;

import edu.ship.engr.presentation.gameobjects.Rectangle;

public interface SnakeGameInterface {
    void startGame();
    void checkAppleCollision(Rectangle snakeHead);
}
