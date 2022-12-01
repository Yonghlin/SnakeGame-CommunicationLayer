package edu.ship.engr.messages;

import edu.ship.engr.presentation.gameobjects.Rectangle;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Direction {
    private boolean host;
    private int clock;
    private final String direction;
    private String prevDir;
    private int otherGamesTick;

    /**
     *
     * @param host
     * @param clock
     * @param direction
     * @param prevDir
     * @param otherGamesTick
     */
    public Direction(boolean host, int clock, String direction, String prevDir, int otherGamesTick) {
        this.host = host;
        this.clock = clock;
        this.direction = direction;
        this.otherGamesTick = otherGamesTick;
        this.prevDir = prevDir;
    }

    /**
     *
     * @param p
     */
    public Direction(LinkedHashMap<String, Object> p)
    {
        this.host = (boolean) p.get("host");
        this.direction = p.get("direction").toString();
        this.clock = (Integer) p.get("clock");
        this.otherGamesTick = (Integer) p.get("otherGamesTick");
        this.prevDir = p.get("prevDir").toString();
    }

    public String getDirection()
    {
        return direction;
    }
    public boolean getHost() { return host; }
    public int getClock() { return clock; }
    public int getOtherGamesTick() { return otherGamesTick; }
    public String getPrevDir() { return prevDir; }

    @Override
    public String toString()
    {
        return "Direction{" +
                "host = " + host +
                ", clock = " + clock +
                ", direction = " + direction +
                ", currentGamesTick = " + otherGamesTick +
                ", prevDir = " + prevDir +
                "}";
    }
}
