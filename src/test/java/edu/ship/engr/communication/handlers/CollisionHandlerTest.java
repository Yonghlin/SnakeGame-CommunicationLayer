package edu.ship.engr.communication.handlers;

import edu.ship.engr.communication.TestUtilities;
import edu.ship.engr.messages.InitializeSnake;
import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.PlayerCollision;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.HostSnakeGame;
import edu.ship.engr.presentation.SnakeGame;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionHandlerTest {
    @Test
    public void testSnakesColliding() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        SnakeGame peerGame = GameFrame.peerSnakeGame;

        assertEquals(false, peerGame.getGameOver());
        assertEquals(false, hostGame.getGameOver());

        //initialized snake in host
        hostGame.addSnake(75, 275, 25, "right", new Color(18,95,227), new Color(12,75,152));
        //sends host snake to peer
        InitializeSnake spawnSnakeToPeer = new InitializeSnake(false,75, 275, 25, "right", "18,95,227", "12,75,152");
        Message<InitializeSnake> msgToSendToPeer = new Message<>(spawnSnakeToPeer);
        Message<?> msgAfterSendingToPeer = TestUtilities.convertToUnpackedJSon(msgToSendToPeer);

        InitializeSnakeHandler peerHandler = new InitializeSnakeHandler();
        peerHandler.processMessage(msgAfterSendingToPeer);

        //initialized snake in peer
        peerGame.addSnake(75, 275, 25, "right", new Color(145,67,67), new Color(150,17,23));
        //send peer snake to host
        InitializeSnake spawnSnakeToHost = new InitializeSnake(true,75, 275, 25, "right", "145,67,67", "150,17,23");
        Message<InitializeSnake> msgToSendToHost = new Message<>(spawnSnakeToHost);
        Message<?> msgAfterSendingToHost = TestUtilities.convertToUnpackedJSon(msgToSendToHost);

        InitializeSnakeHandler hostHandler = new InitializeSnakeHandler();
        peerHandler.processMessage(msgAfterSendingToHost);

        assertEquals(true, hostGame.getGameOver());
        assertEquals(true, peerGame.getGameOver());

    }
}
