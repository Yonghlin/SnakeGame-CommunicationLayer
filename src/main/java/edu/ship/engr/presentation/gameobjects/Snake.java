package edu.ship.engr.presentation.gameobjects;

import edu.ship.engr.messages.Grow;
import edu.ship.engr.messages.InitializeSnake;
import edu.ship.engr.messages.Message;
import edu.ship.engr.peertopeer.PlayRunner;
import edu.ship.engr.presentation.SnakeGame;

import java.util.ArrayList;
import java.awt.Color;

public class Snake {
    private ArrayList<Rectangle> body = new ArrayList<>();
    private String direction;
    private int speed;
    private Color headColor;
    private Color bodyColor;

    /**
     * Creates a new snake
     * @param startingXPos the snakes starting x position
     * @param startingYPos the snakes starting y position
     * @param speed the snakes speed
     */
    public Snake(int startingXPos, int startingYPos, int speed, Color headColor, Color bodyColor) {
        Rectangle head = new Rectangle(startingXPos, startingYPos);
        body.add(head);

        Rectangle bodySegment = new Rectangle(head.getXPosition() - SnakeGame.UNIT_SIZE, head.getYPosition());
        body.add(bodySegment);

        this.direction = "right";
        this.speed = speed;
        this.headColor = headColor;
        this.bodyColor = bodyColor;
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

        body = newBody;
    }

//    0 1 2 3
//    H B B T

    public void rollback(int rollback, String prevDir) {
        for (int rollBackAmt = 0; rollBackAmt < rollback; rollBackAmt++) {
            ArrayList<Rectangle> newBody = new ArrayList<>();

            for (int i = 0; i < body.size() - 1; i++) {
                Rectangle previousBodyPart = body.get(i + 1);
                Rectangle newBodyPart = new Rectangle(previousBodyPart.getXPosition(), previousBodyPart.getYPosition());
                newBody.add(newBodyPart);
            }
            Rectangle oldTail = body.get(body.size() - 1);
            Rectangle newTail = new Rectangle(oldTail.getXPosition(), oldTail.getYPosition());

            switch (prevDir) {
                case "right":
                    newTail.setXPosition(-speed);
                    break;
                case "left":
                    newTail.setXPosition(speed);
                    break;
                case "up":
                    newTail.setYPosition(speed);
                    break;
                case "down":
                    newTail.setYPosition(-speed);
                    break;
            }
            newBody.add(newTail);

            body = newBody;
        }
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
}
