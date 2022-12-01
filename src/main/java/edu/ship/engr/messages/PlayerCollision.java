package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class PlayerCollision {
    private boolean host;
    private int clock;

    public PlayerCollision(boolean host, int clock) {
        this.host = host;
        this.clock = clock;
    }

    public PlayerCollision(LinkedHashMap<String, Object> p) {
        host = (boolean) p.get("host");
        clock = (Integer) p.get("clock");
    }

    @Override
    public String toString() { return "Player collision detected!"; }

    public boolean getHost() { return host; }
    public int getClock() {return clock;}
}
