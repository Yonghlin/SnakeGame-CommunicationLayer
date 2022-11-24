package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.AppleLocation;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;

import java.util.LinkedHashMap;

public class AppleHandler implements Handler {
    @Override
    public void processMessage(Message<?> msg) {
        AppleLocation applemsg = new AppleLocation((LinkedHashMap<String,Object>) msg.getObject());
        System.out.println(applemsg);

        GameFrame.peerSnakeGame.setApple(applemsg.getx(), applemsg.gety());
        //TODO: spawn apple in PeerSnakeGame
    }
}
