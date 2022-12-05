package edu.ship.engr.communication.handlers;

import edu.ship.engr.communication.TestUtilities;
import edu.ship.engr.messages.InitializeSnake;
import edu.ship.engr.messages.Message;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;
import edu.ship.engr.presentation.gameobjects.Snake;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class InitializeSnakeHandlerTest {

    /**
     * test on adding the peer snake on the host game
     * Checking if the other snake is on the game frame after
     * sending the initializeSnake msg and processing it from the handler
     * @throws IOException
     */
    @Test
    public void initializeSnakeOnHost() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;

        Snake peerSnakeOnHost = hostGame.getOtherSnake();
        assertNull(peerSnakeOnHost);

        InitializeSnake msgToProcess = new InitializeSnake(false,75, 275, 25, "right", "18,95,227", "12,75,152");
        Message<InitializeSnake> msgToSendToHost = new Message<>(msgToProcess);
        Message<?> msgAfterSendingToHost = TestUtilities.convertToUnpackedJSon(msgToSendToHost);

        InitializeSnakeHandler handler = new InitializeSnakeHandler();
        handler.processMessage(msgAfterSendingToHost);

        assertNotNull(hostGame.getOtherSnake());
    }

    /**
     * test on adding the host snake on the peer game
     * Checking if the other snake is on the game frame after
     * sending the initializeSnake msg and processing it from the handler
     * @throws IOException
     */
    @Test
    public void initializeSnakeOnPeer() throws IOException {
        new GameFrame();
        SnakeGame peerGame = GameFrame.peerSnakeGame;

        Snake hostSnakeOnPeer = peerGame.getOtherSnake();
        assertNull(hostSnakeOnPeer);

        InitializeSnake msgToProcess = new InitializeSnake(true,75, 275, 25, "down", "18,95,227", "12,75,152");
        Message<InitializeSnake> msgToSendToPeer = new Message<>(msgToProcess);
        Message<?> msgAfterSendingToPeer = TestUtilities.convertToUnpackedJSon(msgToSendToPeer);

        InitializeSnakeHandler handler = new InitializeSnakeHandler();
        handler.processMessage(msgAfterSendingToPeer);

        assertNotNull(peerGame.getOtherSnake());
    }
}
