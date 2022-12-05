package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class PlayerDeath {
    private boolean host;

    /**
     * create a player death message
     * @param host
     */
    public PlayerDeath(boolean host) {
        this.host = host;
    }

    /**
     * create a player death from the hash map
     * @param p
     */
    public PlayerDeath(LinkedHashMap<String, Object> p) {
        host = (boolean) p.get("host");
    }

    @Override
    public String toString() { return "Player has died!"; }

    public boolean getHost() { return host; }
}
