package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.*;
import edu.ship.engr.presentation.GameFrame;

import java.util.LinkedHashMap;

public class PlayerLocationHandler implements Handler {

    @Override
    public void processMessage(Message<?> msg) {
        InitializeSnake locationsMsg = new InitializeSnake((LinkedHashMap<String,Object>) msg.getObject());
        System.out.println(locationsMsg);

        //TODO: spawn apple in PeerSnakeGame
        if (locationsMsg.gethost()) {
            GameFrame.peerSnakeGame.addSnake(locationsMsg.getx(), locationsMsg.gety(), locationsMsg.getspeed(), locationsMsg.getheadColor(), locationsMsg.getbodyColor());
        } else {
            GameFrame.hostSnakeGame.addSnake(locationsMsg.getx(), locationsMsg.gety(), locationsMsg.getspeed(), locationsMsg.getheadColor(), locationsMsg.getbodyColor());
        }
    }
}
