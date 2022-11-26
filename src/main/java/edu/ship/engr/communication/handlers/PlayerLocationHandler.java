package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.*;
import edu.ship.engr.peertopeer.PlayRunner;
import edu.ship.engr.presentation.GameFrame;

import java.util.LinkedHashMap;

public class PlayerLocationHandler implements Handler {

    @Override
    public void processMessage(Message<?> msg) {
        PlayerLocation playermsg = new PlayerLocation((LinkedHashMap<String,Object>) msg.getObject());
        System.out.println(playermsg);

        GameFrame.peerSnakeGame.setApple(playermsg.getx(), playermsg.gety());
        //TODO: spawn apple in PeerSnakeGame
    }
}
