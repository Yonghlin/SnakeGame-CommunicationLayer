package edu.ship.engr.communication.handlers;

import edu.ship.engr.communication.TestUtilities;
import edu.ship.engr.messages.InitializeSnake;
import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.PlayerCollision;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.HostSnakeGame;
import edu.ship.engr.presentation.SnakeGame;
import edu.ship.engr.presentation.gameobjects.Snake;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CollisionHandlerTest {

    /**
     *
     * @throws IOException
     */
    @Test
    public void testSnakesColliding() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        hostGame.addSnake(475, 275, 25, "left", new Color(145, 67, 67), new Color(150, 17, 23));
        SnakeGame peerGame = GameFrame.peerSnakeGame;
        peerGame.addSnake(400, 275, 25, "right", new Color(18, 95, 227), new Color(12, 75, 152));

        Snake hostSnake = hostGame.getSnake();

        assertEquals(false, peerGame.getGameOver());
        assertEquals(false, hostGame.getGameOver());

        PlayerCollision hostColliding = new PlayerCollision(true);
        Message<PlayerCollision> msgToSendToHost = new Message<>(hostColliding);
        Message<?> msgAfterSendingToHost = TestUtilities.convertToUnpackedJSon(msgToSendToHost);

        PlayerCollisionHandler hostHandler = new PlayerCollisionHandler();
        hostHandler.processMessage(msgAfterSendingToHost);

        hostSnake.move();
        hostSnake.move();

        hostGame.checkCollision();

        assertEquals(true, hostGame.getGameOver());
        assertEquals(true, peerGame.getGameOver());
    }

    /**
     *
     * @throws IOException
     */
    @Test
    public void testSnakeCollidesOnWall() throws IOException {
        new GameFrame();
        SnakeGame hostGame = GameFrame.hostSnakeGame;
        hostGame.addSnake(475, 275, 25, "left", new Color(145, 67, 67), new Color(150, 17, 23));
        SnakeGame peerGame = GameFrame.peerSnakeGame;
        peerGame.addSnake(400, 275, 25, "right", new Color(18, 95, 227), new Color(12, 75, 152));

        Snake hostSnake = hostGame.getSnake();

        assertEquals(false, peerGame.getGameOver());
        assertEquals(false, hostGame.getGameOver());

        hostSnake.setDirection("up");
        hostSnake.move();
        hostSnake.setDirection("right");
        hostSnake.move();
        hostSnake.move();
        hostSnake.move();
        hostSnake.move();
        hostSnake.move();
        hostSnake.move();
        hostSnake.move();

        hostGame.checkCollision();

        PlayerCollision hostColliding = new PlayerCollision(true);
        Message<PlayerCollision> msgToSendToHost = new Message<>(hostColliding);
        Message<?> msgAfterSendingToHost = TestUtilities.convertToUnpackedJSon(msgToSendToHost);

        PlayerCollisionHandler hostHandler = new PlayerCollisionHandler();
        hostHandler.processMessage(msgAfterSendingToHost);

        assertEquals(true, hostGame.getGameOver());
        assertEquals(true, peerGame.getGameOver());
    }
}
