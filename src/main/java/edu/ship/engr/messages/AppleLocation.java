package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class AppleLocation {
    private final int x;
    private final int y;
    private boolean host;
    private int clock;

    public AppleLocation(boolean host, int clock, int x, int y) {
        this.host = host;
        this.x = x;
        this.y = y;
        this.clock = clock;
    }

    public AppleLocation(LinkedHashMap<String, Object> p)
    {
        x = (Integer) p.get("x");
        y = (Integer) p.get("y");
        host = (boolean) p.get("host");
        clock = (Integer) p.get("clock");
    }

    public int getClock() {
        return clock;
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
                ", clock = " + clock +
                "}";
    }
}
