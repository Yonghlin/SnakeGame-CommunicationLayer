package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.Grow;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;

import java.util.LinkedHashMap;

public class GrowHandler implements  Handler {
    @Override
    public void processMessage(Message<?> msg) {
        Grow growMsg = new Grow((LinkedHashMap<String, Object>) msg.getObject());
        System.out.println(growMsg);

        SnakeGame gameToProcess = growMsg.getHost() ? GameFrame.peerSnakeGame : GameFrame.hostSnakeGame;
        gameToProcess.growOtherSnake();
    }
}
