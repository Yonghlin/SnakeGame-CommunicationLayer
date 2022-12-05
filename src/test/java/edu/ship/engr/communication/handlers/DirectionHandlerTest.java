package edu.ship.engr.communication.handlers;

import edu.ship.engr.communication.TestUtilities;
import edu.ship.engr.messages.Direction;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;
import edu.ship.engr.presentation.gameobjects.Rectangle;
import edu.ship.engr.presentation.gameobjects.Snake;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
     * Makes sure the oldest saved position is removed from the list of saved positions once it reaches the max capacity
     */
    @Test
    public void testMaxRollback() {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        hostGame.addSnake(475, 275, 25, "left", new Color(145, 67, 67), new Color(150, 17, 23));
        SnakeGame peerGame = GameFrame.peerSnakeGame;
        peerGame.addSnake(400, 275, 25, "right", new Color(18, 95, 227), new Color(12, 75, 152));

        Snake hostSnake = hostGame.getSnake();

        hostSnake.move();
        ArrayList<Rectangle> firstSavedPosition = hostSnake.getPreviousBodyPositions().get(0);

        hostSnake.move();
        ArrayList<Rectangle> secondSavedPosition = hostSnake.getPreviousBodyPositions().get(1);

        hostSnake.move();
        hostSnake.move();
        hostSnake.move();

        assertEquals(firstSavedPosition, hostSnake.getPreviousBodyPositions().get(0));

        // Oldest saved position should get removed after this move
        hostSnake.move();

        assertNotEquals(firstSavedPosition, hostSnake.getPreviousBodyPositions().get(0));
        assertEquals(secondSavedPosition, hostSnake.getPreviousBodyPositions().get(0));
        assertEquals(5, hostSnake.getPreviousBodyPositions().size());
    }

    @Test
    public void testRollbackGreaterThanOne() throws IOException {
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

        hostSnake.move();
        hostSnakeOnPeer.move();
        testSnakePositionOnBoth(hostSnake, hostSnakeOnPeer, 450, 275);

        hostSnake.setDirection("up");
        hostSnakeOnPeer.setDirection("up");

        hostSnake.move();
        hostSnakeOnPeer.move();
        testSnakePositionOnBoth(hostSnake, hostSnakeOnPeer, 450, 250);

        hostSnake.move();
        hostSnakeOnPeer.move();
        testSnakePositionOnBoth(hostSnake, hostSnakeOnPeer, 450, 225);

        hostSnake.setDirection("right");
        hostSnake.move();

        assertEquals(225, hostSnake.getBody().get(0).getYPosition());
        assertEquals(475, hostSnake.getBody().get(0).getXPosition());

        // desynced move
        hostSnakeOnPeer.move();
        hostSnakeOnPeer.move();
        peerGame.setGameClock(6);

        assertEquals(450, hostSnakeOnPeer.getBody().get(0).getXPosition());
        assertEquals(175, hostSnakeOnPeer.getBody().get(0).getYPosition());

        Direction directionToPeer = new Direction(true, "right", 4);
        Message<Direction> msgToSendToPeer = new Message<>(directionToPeer);
        Message<?> msgAfterSendingToPeer = TestUtilities.convertToUnpackedJSon(msgToSendToPeer);

        DirectionHandler peerHandler = new DirectionHandler();
        peerHandler.processMessage(msgAfterSendingToPeer);

        // resynced after direction processed
        testSnakePositionOnBoth(hostSnake, hostSnakeOnPeer, 475, 225);
    }

    /**
     * Checks a snakes current position against a given x and y for both the host and peer windows
     *
     * @param snake the snake being controlled
     * @param otherSnake the snake being controlled on the opposite client
     * @param testingXPosition x position to test
     * @param testingYPosition y position to test
     */
    public void testSnakePositionOnBoth(Snake snake, Snake otherSnake, int testingXPosition, int testingYPosition) {
        assertEquals(testingXPosition, snake.getBody().get(0).getXPosition());
        assertEquals(testingYPosition, snake.getBody().get(0).getYPosition());
        assertEquals(testingXPosition, otherSnake.getBody().get(0).getXPosition());
        assertEquals(testingYPosition, otherSnake.getBody().get(0).getYPosition());
    }
}
