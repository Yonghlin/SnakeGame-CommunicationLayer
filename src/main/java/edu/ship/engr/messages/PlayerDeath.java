package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class PlayerDeath {
    private boolean host;
    public PlayerDeath(boolean host) { this.host = host; }

    public PlayerDeath(LinkedHashMap<String, Object> p) { host = (boolean) p.get("host"); }

    @Override
    public String toString() { return "Player has died!"; }

    public boolean getHost() { return host; }
}
