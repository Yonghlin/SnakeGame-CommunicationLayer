package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.Direction;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;

import java.util.LinkedHashMap;

public class DirectionHandler implements Handler {

    /**
     * Process the message.  The message that we are given is the result of
     * building an object from the JSon string that was sent.  While the class
     * name it contains will be correct, the object it contains will be a
     * hashmap holding the fields of the object that was being sent.  See the
     * FirstMessageHandler for an example of how to rebuild the original object
     * that was sent.
     *
     * @param msg the message we are to process
     */
    @Override
    public void processMessage(Message<?> msg) {
        Direction direction = new Direction((LinkedHashMap<String,Object>) msg.getObject());
        System.out.println(direction);

        SnakeGame gameToProcess = direction.getHost() ? GameFrame.peerSnakeGame : GameFrame.hostSnakeGame;
        gameToProcess.setOtherSnakeDirection(direction.getDirection());

    }
}
