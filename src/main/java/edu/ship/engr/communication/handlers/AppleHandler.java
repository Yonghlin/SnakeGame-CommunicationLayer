package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.AppleLocation;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;

import java.util.LinkedHashMap;

public class AppleHandler implements Handler {
    @Override
    public void processMessage(Message<?> msg) {
        AppleLocation appleMsg = new AppleLocation((LinkedHashMap<String,Object>) msg.getObject());
        System.out.println(appleMsg);

//        if (appleMsg.getClock() == SnakeGame.clock.getCurrentClock() + 1) {
//            // process message as usual then clear history
//        } else if (appleMsg.getClock() == SnakeGame.clock.getCurrentClock()) {
//            // idk what to do here
//        } else {
//            // Rollback to fix sync issue, process the received action, clear history, then continue
//        }

        SnakeGame gameToProcess = appleMsg.gethost() ? GameFrame.peerSnakeGame : GameFrame.hostSnakeGame;
        gameToProcess.setApple(appleMsg.getx(), appleMsg.gety());
    }
}
