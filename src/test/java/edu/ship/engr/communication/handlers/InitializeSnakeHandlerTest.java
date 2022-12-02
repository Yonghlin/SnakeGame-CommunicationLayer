package edu.ship.engr.communication.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InitializeSnakeHandlerTest {

//    @Test
//    public void initializeSnake() throws IOException {
//        InitializeSnake msgToProcess = new InitializeSnake(true, 0, 1, 1,5, "down", "red", "red");
//        Message<InitializeSnake> msgToSend = new Message<>(msgToProcess);
//        Message<?> msgAfterSending = TestUtilities.convertToUnpackedJSon(msgToSend);
//
//        InitializeSnakeHandler handler = new InitializeSnakeHandler();
//        handler.processMessage(msgAfterSending);
//
//        // A FirstMessageReply should have been queued
//        ArrayList<Message<?>> queuedMessages =
//                PlayRunner.messageAccumulator.getPendingMsgs();
//        assertEquals(1,queuedMessages.size());
//        assertEquals(ReplyObject.class,queuedMessages.get(0).getObjectType() );
//    }
}
