package edu.ship.engr.communication.handlers;

import edu.ship.engr.communication.TestUtilities;
import edu.ship.engr.messages.Direction;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;
import edu.ship.engr.presentation.gameobjects.Snake;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionHandlerTest {

    /**
     * Makes sure a snakes direction updates on the other players screen
     * @throws IOException
     */
    @Test
    public void testPlayerHasCorrectDirection() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        hostGame.addSnake(475, 275, 25, "left", new Color(145, 67, 67), new Color(150, 17, 23));
        SnakeGame peerGame = GameFrame.peerSnakeGame;
        peerGame.addSnake(400, 275, 25, "right", new Color(18, 95, 227), new Color(12, 75, 152));

        Snake hostSnake = hostGame.getSnake();
        Snake hostSnakeOnPeer = peerGame.getOtherSnake();

        assertEquals("right", hostSnake.getDirection());
        hostSnake.setDirection("left");
        assertEquals("left", hostSnake.getDirection());

        Direction directionToPeer = new Direction(true, "left", 1);
        Message<Direction> msgToSendToPeer = new Message<>(directionToPeer);
        Message<?> msgAfterSendingToPeer = TestUtilities.convertToUnpackedJSon(msgToSendToPeer);

        DirectionHandler peerHandler = new DirectionHandler();
        peerHandler.processMessage(msgAfterSendingToPeer);

        assertEquals("left", hostSnakeOnPeer.getDirection());
    }

    /**
     * Makes sure a snake gets placed in the correct position if it becomes out of sync
     */
    @Test
    public void testSnakeRollback() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        hostGame.addSnake(475, 275, 25, "left", new Color(145, 67, 67), new Color(150, 17, 23));
        SnakeGame peerGame = GameFrame.peerSnakeGame;
        peerGame.addSnake(400, 275, 25, "right", new Color(18, 95, 227), new Color(12, 75, 152));

        Snake hostSnake = hostGame.getSnake();
        Snake hostSnakeOnPeer = peerGame.getOtherSnake();

        hostSnake.move();
        hostSnakeOnPeer.move();
        testSnakePositionOnBoth(hostSnake, hostSnakeOnPeer, 425, 275);

        hostSnake.setDirection("up");
        hostSnakeOnPeer.setDirection("up");

        hostSnake.move();
        hostSnakeOnPeer.move();
        testSnakePositionOnBoth(hostSnake, hostSnakeOnPeer, 425, 250);

        hostSnake.setDirection("right");
        hostSnake.move();

        // desynced move
        hostSnakeOnPeer.move();
        peerGame.setGameClock(4);

        assertEquals(hostSnake.getBody().get(0).getYPosition(), 250);
        assertEquals(hostSnakeOnPeer.getBody().get(0).getYPosition(), 225);

        Direction directionToPeer = new Direction(true, "right", 3);
        Message<Direction> msgToSendToPeer = new Message<>(directionToPeer);
        Message<?> msgAfterSendingToPeer = TestUtilities.convertToUnpackedJSon(msgToSendToPeer);

        DirectionHandler peerHandler = new DirectionHandler();
        peerHandler.processMessage(msgAfterSendingToPeer);

        // resynced after direction processed
        testSnakePositionOnBoth(hostSnake, hostSnakeOnPeer, 450, 250);
    }

    /**
     * Checks a snakes current position against a given x and y for both the host and peer windows
     * @param snake the snake being controlled
     * @param otherSnake the snake being controlled on the opposite client
     * @param testingXPosition x position to test
     * @param testingYPosition y position to test
     */
    public void testSnakePositionOnBoth(Snake snake, Snake otherSnake, int testingXPosition, int testingYPosition) {
        assertEquals(snake.getBody().get(0).getXPosition(), testingXPosition);
        assertEquals(snake.getBody().get(0).getYPosition(), testingYPosition);
        assertEquals(otherSnake.getBody().get(0).getXPosition(), testingXPosition);
        assertEquals(otherSnake.getBody().get(0).getYPosition(), testingYPosition);
    }
}
