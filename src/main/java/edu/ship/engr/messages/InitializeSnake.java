package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class InitializeSnake {
    private final int x;
    private final int y;
    private final int speed;
    private String headColor;
    private String bodyColor;
    private String startingDirection;
    private boolean host;

    /**
     * Create a snake location for Peer or Host
     * @param host
     * @param x
     * @param y
     * @param speed
     * @param startingDirection
     * @param headColor
     * @param bodyColor
     */
    public InitializeSnake(boolean host, int x, int y, int speed, String startingDirection, String headColor, String bodyColor) {
        this.host = host;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.startingDirection = startingDirection;
        this.headColor = headColor;
        this.bodyColor = bodyColor;
    }

    /**
     * Create snake from the hash map
     * @param p
     */
    public InitializeSnake(LinkedHashMap<String, Object> p) {
        host = (boolean) p.get("host");
        x = (Integer) p.get("x");
        y = (Integer) p.get("y");
        speed = (Integer) p.get("speed");
        startingDirection = p.get("startingDirection").toString();
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
                ", startingDirection = " + startingDirection +
                ", headColor = " + headColor +
                ", bodyColor = " + bodyColor +
                '}';
    }

    public boolean getHost() { return host; }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getSpeed() { return speed; }
    public String getStartingDirection() { return startingDirection; }
    public String getHeadColor() { return headColor; }
    public String getBodyColor() { return bodyColor; }
}
