package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class AppleLocation {
    private final int x;
    private final int y;
    private boolean host;

    /**
     * Create a new apple location message
     * @param host host sending message
     * @param x x position of the apple
     * @param y y position of the apple
     */
    public AppleLocation(boolean host, int x, int y) {
        this.host = host;
        this.x = x;
        this.y = y;
    }

    /**
     * Create a new apple from the hash map
     * @param p the objects map
     */
    public AppleLocation(LinkedHashMap<String, Object> p)
    {
        x = (Integer) p.get("x");
        y = (Integer) p.get("y");
        host = (boolean) p.get("host");
    }

    /**
     * @return true if this is the hosts message
     */
    public boolean getHost() {
        return host;
    }

    public int getx()
    {
        return x;
    }

    public int gety()
    {
        return y;
    }


    @Override
    public String toString()
    {
        return "AppleLocation{" +
                "X = " + x +
                ", Y = " + y +
                ", host = " + host +
                "}";
    }
}
