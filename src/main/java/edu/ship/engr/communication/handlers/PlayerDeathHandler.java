package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.PlayerDeath;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;

import java.util.LinkedHashMap;

public class PlayerDeathHandler implements Handler {
    @Override
    public void processMessage(Message<?> msgFromJSon) {
        PlayerDeath msg = new PlayerDeath((LinkedHashMap<String, Object>) msgFromJSon.getObject());
        System.out.println(msg);

        SnakeGame gameToProcess = msg.getHost() ? GameFrame.peerSnakeGame : GameFrame.hostSnakeGame;
        gameToProcess.endGame(false);
    }
}
