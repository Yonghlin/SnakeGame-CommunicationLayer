package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class PlayerLocation {
    private final int x;
    private final int y;

    public PlayerLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PlayerLocation(LinkedHashMap<String, Object> p) {
        x = (Integer) p.get("x");
        y = (Integer) p.get("y");
    }

    @Override
    public String toString()
    {
        return "PlayerLocation{" +
                "X = " + x +
                ", Y = " + y +
                '}';
    }

    public int getx()
    {
        return x;
    }
    public int gety()
    {
        return y;
    }
}
