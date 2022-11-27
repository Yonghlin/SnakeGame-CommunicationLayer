package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class AppleLocation {
    private final int x;
    private final int y;
    private boolean host;

    public AppleLocation(int x, int y, boolean host) {
        this.x = x;
        this.y = y;
        this.host = host;
    }

    public AppleLocation(LinkedHashMap<String, Object> p)
    {
        x = (Integer) p.get("x");
        y = (Integer) p.get("y");
        host = (boolean) p.get("host");
    }

    public boolean gethost() {
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
