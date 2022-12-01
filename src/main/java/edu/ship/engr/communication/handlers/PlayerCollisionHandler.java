package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.AppleLocation;
import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.PlayerCollision;
import edu.ship.engr.messages.PlayerDeath;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;

import java.util.LinkedHashMap;

public class PlayerCollisionHandler implements Handler {
    @Override
    public void processMessage(Message<?> msgFromJSon) {
        PlayerCollision collide = new PlayerCollision((LinkedHashMap<String, Object>) msgFromJSon.getObject());
        System.out.println(collide);

        SnakeGame gameToProcess = collide.getHost() ? GameFrame.peerSnakeGame : GameFrame.hostSnakeGame;
        gameToProcess.endGame();
    }
}
