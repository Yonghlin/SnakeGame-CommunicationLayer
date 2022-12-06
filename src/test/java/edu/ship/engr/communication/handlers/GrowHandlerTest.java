package edu.ship.engr.communication.handlers;

import edu.ship.engr.communication.TestUtilities;
import edu.ship.engr.messages.Direction;
import edu.ship.engr.messages.Grow;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;
import edu.ship.engr.presentation.gameobjects.Snake;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrowHandlerTest {
    /**
     * Makes sure the snake grows on the peers window
     */
    @Test
    public void testSnakeGrowsByOne() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        hostGame.addSnake(475, 275, 25, "left", new Color(145, 67, 67), new Color(150, 17, 23));
        SnakeGame peerGame = GameFrame.peerSnakeGame;
        peerGame.addSnake(400, 275, 25, "right", new Color(18, 95, 227), new Color(12, 75, 152));

        Snake hostSnake = hostGame.getSnake();
        Snake hostSnakeOnPeer = peerGame.getOtherSnake();

        assertEquals(2, hostSnake.getBody().size());

        hostSnake.grow();

        assertEquals(3, hostSnake.getBody().size());

        Grow growToPeer = new Grow(true);
        Message<Direction> msgToSendToPeer = new Message<>(growToPeer);
        Message<?> msgAfterSendingToPeer = TestUtilities.convertToUnpackedJSon(msgToSendToPeer);

        GrowHandler growHandler = new GrowHandler();
        growHandler.processMessage(msgAfterSendingToPeer);

        assertEquals(3, hostSnakeOnPeer.getBody().size());
    }
}
