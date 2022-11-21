package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.FirstObjectToSend;
import edu.ship.engr.messages.InitializeGame;
import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.ReplyObject;
import edu.ship.engr.peertopeer.PlayRunner;

import java.util.LinkedHashMap;

public class InitializeGameHandler implements Handler {
    /**
     * Process the message.  The message that we are given is the result of
     * building an object from the JSon string that was sent.  While the class
     * name it contains will be correct, the object it contains will be a
     * hashmap holding the fields of the object that was being sent.  See the
     * FirstMessageHandler for an example of how to rebuild the original object
     * that was sent.
     *
     * @param msgFromJSon the message we are to process
     */
    @Override
    public void processMessage(Message<?> msgFromJSon) {
        InitializeGame msg = new InitializeGame((LinkedHashMap<String, Object>) msgFromJSon.getObject());
        System.out.println(msg);

        PlayRunner.messageAccumulator.queueMessage(new Message<>(new ReplyObject("Got " + msg
                + "!!!!")));
    }
}
