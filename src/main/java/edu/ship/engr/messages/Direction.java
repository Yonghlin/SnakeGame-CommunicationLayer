package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class Direction {
    private boolean host;
    private int clock;
    private final String direction;

    public Direction(boolean host, int clock, String direction) {
        this.host = host;
        this.clock = clock;
        this.direction = direction;
    }

    public Direction(LinkedHashMap<String, Object> p)
    {
        this.host = (boolean) p.get("host");
        this.direction = p.get("direction").toString();
        this.clock = (Integer) p.get("clock");
    }

    public String getDirection()
    {
        return direction;
    }
    public boolean getHost() { return host; }
    public int getClock() { return clock; }

    @Override
    public String toString()
    {
        return "Direction{" +
                "host = " + host +
                ", clock = " + clock +
                ", direction = " + direction +
                "}";
    }
}
