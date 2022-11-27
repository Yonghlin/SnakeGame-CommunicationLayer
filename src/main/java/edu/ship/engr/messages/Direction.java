package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class Direction {
    private boolean host;
    private final String direction;

    public Direction(boolean host, String direction) {
        this.host = host;
        this.direction = direction;
    }

    public Direction(LinkedHashMap<String, Object> p)
    {
        this.host = (boolean) p.get("host");
        this.direction = p.get("direction").toString();
    }

    public String getDirection()
    {
        return direction;
    }
    public boolean getHost() { return host; }

    @Override
    public String toString()
    {
        return "Direction{" +
                "host = " + host +
                ", direction = " + direction +
                "}";
    }
}
