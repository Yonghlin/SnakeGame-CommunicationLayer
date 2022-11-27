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

    public InitializeSnake(boolean host, int x, int y, int speed, Color headColor, Color bodyColor) {
        this.host = host;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.headColor = headColor;
        this.bodyColor = bodyColor;
    }

    public InitializeSnake(LinkedHashMap<String, Object> p) {
        host = (boolean) p.get("host");
        x = (Integer) p.get("x");
        y = (Integer) p.get("y");
        speed = (Integer) p.get("speed");

//        if (host) {
//            headColor =
//                    bodyColor =
//        } else {
//            headColor =
//             bodyColor =
//        }

        headColor = getColor(p.get("headColor").toString().split(", "));
        System.out.println("headColor is " + headColor);
        // headColor = getColor(p.get("headColor"));
        // bodyColor = p.get("bodyColor").toString();
        bodyColor = getColor(p.get("bodyColor").toString().split(", "));
        System.out.println("bodyColor is " + bodyColor);
    }

    public Color getColor(String[] color) {
        try {
            return new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2]));
        } catch (Exception e) {
            System.out.println("e = " + e);
        }

        return null;
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
//        String[] rgbValues = headColor.split(",");
//        return new Color(Integer.parseInt(rgbValues[0]),Integer.parseInt(rgbValues[1]),Integer.parseInt(rgbValues[2]));
        return headColor;
    }
    public Color getbodyColor() {
//        String[] rgbValues = bodyColor.split(",");
//        return new Color(Integer.parseInt(rgbValues[0]),Integer.parseInt(rgbValues[1]),Integer.parseInt(rgbValues[2]));
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
