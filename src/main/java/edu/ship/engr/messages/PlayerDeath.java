package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class PlayerDeath {
    private boolean host;
    private int clock;

    public PlayerDeath(boolean host, int clock) {
        this.host = host;
        this.clock = clock;
    }

    public PlayerDeath(LinkedHashMap<String, Object> p) {
        host = (boolean) p.get("host");
        clock = (Integer) p.get("clock");
    }

    @Override
    public String toString() { return "Player has died!"; }

    public boolean getHost() { return host; }
    public int getClock() { return clock; }
    public int getClock() {return clock;}
}
