package edu.ship.engr.messages;

import java.awt.*;
import java.util.LinkedHashMap;

public class InitializeSnake {
    private final int x;
    private final int y;
    private final int speed;
    private Color headColor;
    private Color bodyColor;
    private boolean host;

    public InitializeSnake(boolean host, int x, int y, int speed) {
        this.host = host;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public InitializeSnake(LinkedHashMap<String, Object> p) {
        host = (boolean) p.get("host");
        x = (Integer) p.get("x");
        y = (Integer) p.get("y");
        speed = (Integer) p.get("speed");

        if(host) {
            headColor = new Color(18, 95, 227);
            bodyColor = new Color(12, 75, 152);
        } else {
            headColor = new Color(145, 67, 67);
            bodyColor = new Color(150, 17, 23);
        }
    }
    
    public boolean gethost() { return host; }
    public int getx()
    {
        return x;
    }
    public int gety()
    {
        return y;
    }
    public int getspeed() { return speed; }
    public Color getheadColor() {
        return headColor;
    }
    public Color getbodyColor() {
        return bodyColor;
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
}
