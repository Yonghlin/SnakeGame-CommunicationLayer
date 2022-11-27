package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.AppleLocation;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;

import java.util.LinkedHashMap;

public class AppleHandler implements Handler {
    @Override
    public void processMessage(Message<?> msg) {
        AppleLocation appleMsg = new AppleLocation((LinkedHashMap<String,Object>) msg.getObject());
        System.out.println(appleMsg);

        if (appleMsg.gethost()) {
            GameFrame.peerSnakeGame.setApple(appleMsg.getx(), appleMsg.gety());
        } else {
            GameFrame.hostSnakeGame.setApple(appleMsg.getx(), appleMsg.gety());
        }
    }
}
