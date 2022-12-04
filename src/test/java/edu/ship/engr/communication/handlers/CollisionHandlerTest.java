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
//    @Test
//    public void InitializingSnakesTest() throws IOException {
//        //initialized snake in host
//        //sends host snake to peer
//        InitializeSnake spawnSnakeToPeer = new InitializeSnake(true,75, 275, 25, "right", "18,95,227", "12,75,152");
//        Message<InitializeSnake> msgToSendToPeer = new Message<>(spawnSnakeToPeer);
//        Message<?> msgAfterSendingToPeer = TestUtilities.convertToUnpackedJSon(msgToSendToPeer);
//
//        InitializeSnakeHandler peerHandler = new InitializeSnakeHandler();
//        peerHandler.processMessage(msgAfterSendingToPeer);
//
//        //initialized snake in peer
//        //send peer snake to host
//        InitializeSnake spawnSnakeToHost = new InitializeSnake(false,75, 275, 25, "right", "145,67,67", "150,17,23");
//        Message<InitializeSnake> msgToSendToHost = new Message<>(spawnSnakeToHost);
//        Message<?> msgAfterSendingToHost = TestUtilities.convertToUnpackedJSon(msgToSendToHost);
//
//        InitializeSnakeHandler hostHandler = new InitializeSnakeHandler();
//        hostHandler.processMessage(msgAfterSendingToHost);
//    }
    @Test
    public void SnakesCollidingTest() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        SnakeGame peerGame = GameFrame.peerSnakeGame;

        assertEquals(false, peerGame.getGameOver());
        assertEquals(false, hostGame.getGameOver());

        PlayerCollision hostColliding = new PlayerCollision(true);
        Message<PlayerCollision> msgToSendToHost = new Message<>(hostColliding);
        Message<?> msgAfterSendingToHost = TestUtilities.convertToUnpackedJSon(msgToSendToHost);

        PlayerCollisionHandler hostHandler = new PlayerCollisionHandler();
        hostHandler.processMessage(msgAfterSendingToHost);

        hostGame.checkCollision();

        assertEquals(true, hostGame.getGameOver());
        assertEquals(true, peerGame.getGameOver());

    }
}
