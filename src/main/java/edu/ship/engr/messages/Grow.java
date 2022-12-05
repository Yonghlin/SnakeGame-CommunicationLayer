package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class Grow {
    private boolean host;

    /**
     * Create a grow message
     * @param host
     */
    public Grow(boolean host) {
        this.host = host;
    }

    /**
     * Host or Peer grow from the hash map
     * @param p
     */
    public Grow(LinkedHashMap<String, Object> p)
    {
        host = (boolean) p.get("host");
    }

    /**
     * @return true if this is the hosts message
     */
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