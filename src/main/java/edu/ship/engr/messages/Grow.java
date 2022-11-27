package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class Grow {
    private boolean host;

    public Grow(boolean host) {
        this.host = host;
    }

    public Grow(LinkedHashMap<String, Object> p)
    {
        host = (boolean) p.get("host");
    }

    public boolean getHost() {
        return host;
    }

    @Override
    public String toString()
    {
        return "Grow{" +
                "host = " + host +
                "}";
    }
}