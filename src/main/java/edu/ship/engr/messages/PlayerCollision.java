package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class PlayerCollision {
    private boolean host;

    public PlayerCollision(boolean host) {
        this.host = host;
    }

    public PlayerCollision(LinkedHashMap<String, Object> p) {
        host = (boolean) p.get("host");
    }

    @Override
    public String toString() { return "Player collision detected!"; }
    public boolean getHost() { return host; }
}
