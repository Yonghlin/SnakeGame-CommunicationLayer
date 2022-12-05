package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class PlayerCollision {
    private boolean host;

    /**
     * create a Player collision message
     * @param host
     */
    public PlayerCollision(boolean host) {
        this.host = host;
    }

    /**
     * create a player collision from the hash map
     * @param p
     */
    public PlayerCollision(LinkedHashMap<String, Object> p) {
        host = (boolean) p.get("host");
    }

    @Override
    public String toString() { return "Player collision detected!"; }
    /**
     * @return true if this is the hosts message
     */
    public boolean getHost() { return host; }
}
