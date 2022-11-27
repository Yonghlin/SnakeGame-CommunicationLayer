package edu.ship.engr.communication.handlers;

import edu.ship.engr.messages.*;
import edu.ship.engr.presentation.GameFrame;
import edu.ship.engr.presentation.SnakeGame;

import java.awt.*;
import java.util.LinkedHashMap;

public class InitializeSnakeHandler implements Handler {

    @Override
    public void processMessage(Message<?> msg) {
        InitializeSnake initializeMsg = new InitializeSnake((LinkedHashMap<String,Object>) msg.getObject());
        System.out.println(initializeMsg);

        String[] headRGBValues = initializeMsg.getHeadColor().split(",");
        Color headColor = new Color(Integer.parseInt(headRGBValues[0]), Integer.parseInt(headRGBValues[1]), Integer.parseInt(headRGBValues[2]));

        String[] bodyRGBValues = initializeMsg.getBodyColor().split(",");
        Color bodyColor = new Color(Integer.parseInt(bodyRGBValues[0]), Integer.parseInt(bodyRGBValues[1]), Integer.parseInt(bodyRGBValues[2]));

        SnakeGame gameToProcess = initializeMsg.getHost() ? GameFrame.peerSnakeGame : GameFrame.hostSnakeGame;
        gameToProcess.addSnake(initializeMsg.getX(), initializeMsg.getY(), initializeMsg.getSpeed(), headColor, bodyColor);
    }
}
