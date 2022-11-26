package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class PlayerDeath {
    private boolean playerDeath = false;
    public PlayerDeath(boolean playerDeath) { playerDeath = playerDeath; }

    public PlayerDeath(LinkedHashMap<String, Object> p) { playerDeath = (boolean) p.get("playerDeath"); }

    @Override
    public String toString() { return "Player has died!"; }

    public boolean getPlayerDeath() { return playerDeath; }
}
