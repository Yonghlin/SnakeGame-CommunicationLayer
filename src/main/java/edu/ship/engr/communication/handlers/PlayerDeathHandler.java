package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.PlayerDeath;
import edu.ship.engr.messages.ReplyObject;
import edu.ship.engr.peertopeer.PlayRunner;

import java.util.LinkedHashMap;

public class PlayerDeathHandler implements Handler {
    @Override
    public void processMessage(Message<?> msgFromJSon) {
        PlayerDeath msg = new PlayerDeath((LinkedHashMap<String, Object>) msgFromJSon.getObject());
        System.out.println(msg);

        PlayRunner.messageAccumulator.queueMessage(new Message<>(new ReplyObject("Got " + msg
                + "!!!!")));
    }
}
