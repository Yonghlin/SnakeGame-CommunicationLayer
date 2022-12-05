package edu.ship.engr.presentation.gameobjects;

import edu.ship.engr.presentation.SnakeGame;

import java.util.ArrayList;
import java.awt.Color;

public class Snake {
    public static final int MAX_ROLLBACK = 5;
    private ArrayList<Rectangle> body = new ArrayList<>();
    private final ArrayList<ArrayList<Rectangle>> previousBodyPositions = new ArrayList<>();
    private String direction;
    private final int speed;
    private final Color headColor;
    private final Color bodyColor;
    private boolean canUpdate;


    /**
     * Creates a new snake
     *
     * @param startingXPos the snakes starting x position
     * @param startingYPos the snakes starting y position
     * @param speed the snakes speed
     * @param startingDirection the direction the snake starts moving in
     * @param headColor the color of the snakes head
     * @param bodyColor the color of the snakes body
     */
    public Snake(int startingXPos, int startingYPos, int speed, String startingDirection, Color headColor, Color bodyColor) {
        Rectangle head = new Rectangle(startingXPos, startingYPos);
        body.add(head);

        Rectangle bodySegment = new Rectangle(head.getXPosition() - SnakeGame.UNIT_SIZE, head.getYPosition());
        body.add(bodySegment);

        this.direction = startingDirection;
        this.speed = speed;
        this.headColor = headColor;
        this.bodyColor = bodyColor;
        this.canUpdate = true;
    }

    /**
     * Uses the snakes old position to move it forward depending on its current direction
     */
    public void move() {
        ArrayList<Rectangle> newBody = new ArrayList<>();

        Rectangle oldHead = body.get(0);
        Rectangle newHead = new Rectangle(oldHead.getXPosition(), oldHead.getYPosition());

        switch (direction) {
            case "right":
                newHead.setXPosition(speed);
                break;
            case "left":
                newHead.setXPosition(-speed);
                break;
            case "up":
                newHead.setYPosition(-speed);
                break;
            case "down":
                newHead.setYPosition(speed);
                break;
        }
        newBody.add(newHead);

        for (int i = 1; i < body.size(); i++) {
            Rectangle previousBodyPart = body.get(i-1);
            Rectangle newBodyPart = new Rectangle(previousBodyPart.getXPosition(), previousBodyPart.getYPosition());
            newBody.add(newBodyPart);
        }

        if (previousBodyPositions.size() == MAX_ROLLBACK) {
            previousBodyPositions.remove(0);
            previousBodyPositions.add(body);
        } else {
            previousBodyPositions.add(body);
        }

        body = newBody;
    }

    /**
     * Rolls the snake back to a previous position
     * @param rollback how many positions to revert the snake by
     */
    public void rollback(int rollback) {
        canUpdate = false;
        int rollbackPositionIndex = (rollback == 1) ? (previousBodyPositions.size() - 1) : (previousBodyPositions.size() - rollback);
        body = previousBodyPositions.get(rollbackPositionIndex);
        move();

        for (int i = rollbackPositionIndex; i < previousBodyPositions.size(); i++) {
            previousBodyPositions.remove(i);
        }
        canUpdate = true;
    }

    /**
     * Adds one length to the snake
     */
    public void grow() {
        Rectangle lastBodyPart = body.get(body.size() -1);
        int lastPartXPos = lastBodyPart.getXPosition();
        int lastPartYPos = lastBodyPart.getYPosition();

        switch (direction) {
            case "right":
                addPartToBody(lastPartXPos - SnakeGame.UNIT_SIZE, lastPartYPos);
                break;
            case "left":
                addPartToBody(lastPartXPos + SnakeGame.UNIT_SIZE, lastPartYPos);
                break;
            case "up":
                addPartToBody(lastPartXPos, lastPartYPos + SnakeGame.UNIT_SIZE);
                break;
            case "down":
                addPartToBody(lastPartXPos, lastPartYPos - SnakeGame.UNIT_SIZE);
                break;
        }
    }

    /**
     * Adds a rectangle to the snakes body at a specified position
     * @param xPosition x position of the rectangle
     * @param yPosition y position of the rectangle
     */
    private void addPartToBody(int xPosition, int yPosition) {
        body.add(new Rectangle(xPosition, yPosition));
    }

    /**
     * @return the snakes body
     */
    public ArrayList<Rectangle> getBody() {
        return body;
    }

    /**
     * @return the head of the snake
     */
    public Rectangle getHead() {
        return body.get(0);
    }

    /**
     * @return the color of the snakes head
     */
    public Color getHeadColor() { return headColor; }

    /**
     * @return the color of the snakes body
     */
    public Color getBodyColor() { return bodyColor; }
    /**
     * Sets the snakes moving direction
     * @param newDirection new direction to move in
     */
    public void setDirection(String newDirection) {
        this.direction = newDirection;
    }

    /**
     * @return the current direction the snakes moving in
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @return the previous positions the snake has been in
     */
    public ArrayList<ArrayList<Rectangle>> getPreviousBodyPositions() {
        return previousBodyPositions;
    }

    /**
     * @return true if the game should update the snake entity
     */
    public boolean getCanUpdate() { return canUpdate; }
}
