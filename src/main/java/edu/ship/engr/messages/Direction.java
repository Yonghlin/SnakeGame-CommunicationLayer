package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class Direction {
    private boolean host;
    private final String direction;
    private int otherGamesTick;

    /**
     * @param host
     * @param direction
     * @param otherGamesTick
     */
    public Direction(boolean host, String direction, int otherGamesTick) {
        this.host = host;
        this.direction = direction;
        this.otherGamesTick = otherGamesTick;
    }

    /**
     *
     * @param p
     */
    public Direction(LinkedHashMap<String, Object> p)
    {
        this.host = (boolean) p.get("host");
        this.direction = p.get("direction").toString();
        this.otherGamesTick = (Integer) p.get("otherGamesTick");
    }

    public String getDirection()
    {
        return direction;
    }
    public boolean getHost() { return host; }
    public int getOtherGamesTick() { return otherGamesTick; }

    @Override
    public String toString()
    {
        return "Direction{" +
                "host = " + host +
                ", direction = " + direction +
                ", currentGamesTick = " + otherGamesTick +
                "}";
    }
}
