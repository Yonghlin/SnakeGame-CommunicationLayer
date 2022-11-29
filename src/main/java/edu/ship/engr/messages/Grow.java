package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class Grow {
    private boolean host;
    private int clock;

    public Grow(boolean host, int clock) {
        this.host = host;
        this.clock = clock;
    }

    public Grow(LinkedHashMap<String, Object> p)
    {
        host = (boolean) p.get("host");
        this.clock = (Integer) p.get("clock");
    }

    public boolean getHost() {
        return host;
    }

    public int getClock() {return clock;}

    @Override
    public String toString()
    {
        return "Grow{" +
                "host = " + host +
                "}";
    }
}