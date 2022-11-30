package edu.ship.engr.messages;

import java.awt.*;
import java.util.LinkedHashMap;

public class InitializeSnake {
    private final int x;
    private final int y;
    private final int speed;
    private String headColor;
    private String bodyColor;
    private boolean host;
    private int clock;

    public InitializeSnake(boolean host, int clock, int x, int y, int speed, String headColor, String bodyColor) {
        this.host = host;
        this.clock = clock;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.headColor = headColor;
        this.bodyColor = bodyColor;
    }

    public InitializeSnake(LinkedHashMap<String, Object> p) {
        host = (boolean) p.get("host");
        clock = (Integer) p.get("clock");
        x = (Integer) p.get("x");
        y = (Integer) p.get("y");
        speed = (Integer) p.get("speed");
        headColor = p.get("headColor").toString();
        bodyColor = p.get("bodyColor").toString();
    }

    @Override
    public String toString()
    {
        return "InitializeSnake{" +
                "host = " + host +
                ", X = " + x +
                ", Y = " + y +
                ", speed = " + speed +
                ", headColor = " + headColor +
                ", bodyColor = " + bodyColor +
                '}';
    }

    public boolean getHost() { return host; }
    public int getClock() {return clock;}
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getSpeed() { return speed; }
    public String getHeadColor() { return headColor; }
    public String getBodyColor() { return bodyColor; }
}
