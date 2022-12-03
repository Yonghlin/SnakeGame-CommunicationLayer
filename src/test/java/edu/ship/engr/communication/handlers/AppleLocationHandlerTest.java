package edu.ship.engr.communication.handlers;

import edu.ship.engr.communication.TestUtilities;
import edu.ship.engr.messages.AppleLocation;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppleLocationHandlerTest {
    @Test
    public void testAppleLocation() throws IOException {
        new GameFrame(true);
        new GameFrame(false);

        GameFrame.hostSnakeGame.setApple(100, 50);
        GameFrame.hostSnakeGame.addSnake(475, 275, 25, "left", new Color(145, 67, 67), new Color(150, 17, 23));

        AppleLocation appleLocation = new AppleLocation(true, 100, 50);
        Message<AppleLocation> msgToSend = new Message<>(appleLocation);
        Message<?> msgAfterSending = TestUtilities.convertToUnpackedJSon(msgToSend);

        AppleLocationHandler handler = new AppleLocationHandler();
        handler.processMessage(msgAfterSending);

        int[] hostAppleLocation = GameFrame.hostSnakeGame.getAppleLocation();
        int[] peerAppleLocation = GameFrame.peerSnakeGame.getAppleLocation();

        assertEquals(hostAppleLocation[0], peerAppleLocation[0]);
        assertEquals(hostAppleLocation[1], peerAppleLocation[1]);

        // The test works but because we are running the actual game, we have to close it
        // As such, the below two statements don't exactly work
        GameFrame.peerSnakeGame.mockEndGame();
        GameFrame.hostSnakeGame.mockEndGame();
    }
}
