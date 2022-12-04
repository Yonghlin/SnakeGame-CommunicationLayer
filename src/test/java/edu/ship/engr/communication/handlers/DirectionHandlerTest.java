package edu.ship.engr.communication.handlers;

import edu.ship.engr.communication.TestUtilities;
import edu.ship.engr.messages.AppleLocation;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;
import edu.ship.engr.presentation.gameobjects.Apple;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionHandlerTest {
    @Test
    public void testPlayHasCorrectDirection() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        SnakeGame peerGame = GameFrame.peerSnakeGame;


    }

    @Test
    public void testAppleLocation() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        SnakeGame peerGame = GameFrame.peerSnakeGame;
        Apple hostApple = hostGame.getApple();
        Apple peerApple = peerGame.getApple();

        // Send to peer
        assertEquals(0, peerApple.getXPosition());
        assertEquals(0, peerApple.getYPosition());

        AppleLocation appleLocationToPeer = new AppleLocation(true, 100, 50);
        Message<AppleLocation> msgToSendToPeer = new Message<>(appleLocationToPeer);
        Message<?> msgAfterSendingToPeer = TestUtilities.convertToUnpackedJSon(msgToSendToPeer);

        AppleLocationHandler peerHandler = new AppleLocationHandler();
        peerHandler.processMessage(msgAfterSendingToPeer);

        assertEquals(100, peerApple.getXPosition());
        assertEquals(50, peerApple.getYPosition());

        // Send to host
        assertEquals(0, hostApple.getXPosition());
        assertEquals(0, hostApple.getYPosition());

        AppleLocation appleLocationToHost = new AppleLocation(false, 150, 70);
        Message<AppleLocation> msgToSendToHost = new Message<>(appleLocationToHost);
        Message<?> msgAfterSendingToHost = TestUtilities.convertToUnpackedJSon(msgToSendToHost);

        AppleLocationHandler hostHandler = new AppleLocationHandler();
        hostHandler.processMessage(msgAfterSendingToHost);

        assertEquals(150, hostApple.getXPosition());
        assertEquals(70, hostApple.getYPosition());
    }
}
