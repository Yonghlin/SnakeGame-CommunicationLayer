package edu.ship.engr.messages;

import edu.ship.engr.presentation.gameobjects.Rectangle;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Direction {
    private boolean host;
    private int clock;
    private final String direction;
    private final String previousDirection;
    private ArrayList<Rectangle> snakeBody;
    private int currentGamesTick = 0;

    public Direction(boolean host, int clock, String direction, String previousDirection, ArrayList<Rectangle> snakeBody, int currentGamesTick) {
        this.host = host;
        this.clock = clock;
        this.direction = direction;
        this.previousDirection = previousDirection;
        this.snakeBody = snakeBody;
        this.currentGamesTick = currentGamesTick;
    }

    public Direction(LinkedHashMap<String, Object> p)
    {
        this.host = (boolean) p.get("host");
        this.direction = p.get("direction").toString();
        this.previousDirection = p.get("previousDirection").toString();
        this.clock = (Integer) p.get("clock");
        this.snakeBody = (ArrayList<Rectangle>) p.get("snakeBody");
        this.currentGamesTick = (Integer) p.get("currentGamesTick");
    }

    public String getDirection()
    {
        return direction;
    }
    public String getPreviousDirection() { return previousDirection; }
    public boolean getHost() { return host; }
    public ArrayList<Rectangle> getSnakeBody() { return snakeBody; }
    public int getClock() { return clock; }
    public int getCurrentGamesTick() { return currentGamesTick; }

    @Override
    public String toString()
    {
        return "Direction{" +
                "host = " + host +
                ", clock = " + clock +
                ", direction = " + direction +
                ", snakeBody = " + snakeBody +
                ", currentGamesTick = " + currentGamesTick +
                "}";
    }
}
