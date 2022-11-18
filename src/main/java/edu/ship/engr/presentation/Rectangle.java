package edu.ship.engr.presentation;

public class Rectangle {
    private int xPosition;
    private int yPosition;

    /**
     * Creates a rectangle
     * @param xPosition the x position of the rectangle
     * @param yPosition the y position of the rectangle
     */
    public Rectangle(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Checks if two rectangles are intersecting
     * @param rectangle the rectangle to check against
     * @return true if the rectangles intersect
     */
    public boolean intersects(Rectangle rectangle) {
        return xPosition == rectangle.getXPosition() && yPosition == rectangle.getYPosition();
    }

    /**
     * @return the rectangles x pos
     */
    public int getXPosition() {
        return this.xPosition;
    }

    /**
     * @return the rectangles y pos
     */
    public int getYPosition() {
        return this.yPosition;
    }

    /**
     * Sets an existing rectangles x pos
     * @param increment the new x pos
     */
    public void setXPosition(int increment) { this.xPosition = this.xPosition + increment; }

    /**
     * Sets an existing rectangles y pos
     * @param increment the new y pos
     */
    public void setYPosition(int increment) { this.yPosition =  this.yPosition + increment; }

}
