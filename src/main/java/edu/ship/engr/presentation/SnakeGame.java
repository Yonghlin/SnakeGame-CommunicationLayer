package edu.ship.engr.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SnakeGame extends JPanel implements KeyListener, ActionListener {
    static final int SCREEN_WIDTH = 550;
    static final int SCREEN_HEIGHT = 550;
    static final int UNIT_SIZE = 25;
    private static final Color backgroundColor = new Color(115,162,78);
    private static final int startingXPos = 75;
    private static final int startingYPos = 0;
    private static final boolean DRAW_GRID = false;
    private Apple apple;
    private GameFrame window;
    private Snake snake;


    /**
     * Creates a new JPanel to contain the snake game
     * @param window the JFrame window
     */
    public SnakeGame(GameFrame window) {
        this.window = window;
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(backgroundColor);
        window.addKeyListener(this);

        startGame();
    }

    /**
     * Starts the game timer and instantiates the players and fruit
     */
    private void startGame() {
        // timer for redrawing the screen
        Timer timer = new Timer(175, this);
        timer.start();

        snake = new Snake(startingXPos, startingYPos, 25);
        apple = new Apple();
    }

    /**
     * Checks all possible collisions that may happen within the game
     */
    private void checkCollision() {
        Rectangle snakeOneHead = snake.getHead();
        ArrayList<Rectangle> snakeOneBody = snake.getBody();
        int currentX = snakeOneHead.getXPosition();
        int currentY = snakeOneHead.getYPosition();

        // Check borders
        if (currentX < 0) {
            endGame();
        }
        if (currentX > SCREEN_WIDTH) {
            endGame();
        }
        if (currentY < 0) {
            endGame();
        }
        if (currentY > SCREEN_HEIGHT) {
            endGame();
        }

        // Check self intersect
        for (int i = 1; i < snake.getBody().size(); i++) {
            Rectangle currentBodyPart = snakeOneBody.get(i);
            if (snakeOneHead.intersects(currentBodyPart)) {
                endGame();
            }
        }

        // Check for fruit
        if (snakeOneHead.intersects(new Rectangle(apple.getXPosition(), apple.getYPosition()))) {
            apple = null;
            apple = new Apple();
            snake.grow();
        }
    }

    /**
     * Ends the game
     */
    private void endGame() {
        System.out.println("You lose!");
        this.window.setVisible(false);

        JFrame parent = new JFrame("Game over!");
        JOptionPane.showMessageDialog(parent, "Your score: " + snake.getBody().size());

        this.window.dispatchEvent(new WindowEvent(this.window, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }

    /**
     * Draws the snake in the window
     * @param g graphics
     */
    private void drawSnake(Graphics g) {
        snake.move();
        checkCollision();

        Graphics2D g2D = (Graphics2D) g;
        ArrayList<Rectangle> snakeOneBody = snake.getBody();

        for (int i = 0; i < snakeOneBody.size(); i++) {
            Rectangle currentBodyPart = snakeOneBody.get(i);
            int currentBodyXPos = currentBodyPart.getXPosition();
            int currentBodyYPos = currentBodyPart.getYPosition();

            if (i == 0) {
                g2D.setPaint(new Color(18, 95, 227));
            } else {
                g2D.setPaint(new Color(12, 75, 152));
            }
            g2D.drawRect(currentBodyXPos, currentBodyYPos, UNIT_SIZE, UNIT_SIZE);
            g2D.fillRect(currentBodyXPos, currentBodyYPos, UNIT_SIZE, UNIT_SIZE);
        }
    }

    /**
     * Draws the apple in the window
     * @param g the graphics
     */
    private void drawApple(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        if (this.apple != null) {
            g2D.setPaint(Color.red);
            g2D.drawRect(this.apple.getXPosition(), this.apple.getYPosition(), UNIT_SIZE, UNIT_SIZE);
            g2D.fillRect(this.apple.getXPosition(),this.apple.getYPosition(), UNIT_SIZE, UNIT_SIZE);
        }
    }

    /**
     * Draws a grid on the window for debugging
     * @param g the graphics
     */
    private void drawGrid(Graphics g) {
        int width = SCREEN_WIDTH;
        int height = SCREEN_HEIGHT;
        int rows = SCREEN_WIDTH / UNIT_SIZE;
        int cols = SCREEN_HEIGHT / UNIT_SIZE;

        // draw the rows
        int rowHt = height / (rows);
        for (int i = 0; i < rows; i++)
            g.drawLine(0, i * rowHt, width, i * rowHt);

        // draw the columns
        int rowWid = width / (cols);
        for (int i = 0; i < cols; i++)
            g.drawLine(i * rowWid, 0, i * rowWid, height);
    }

    /**
     * Update
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (DRAW_GRID) {
            drawGrid(g);
        }

        drawSnake(g);
        drawApple(g);
    }

    /**
     * Listens for a key press
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == 39 && !snake.getDirection().equals("left")) {
            snake.setDirection("right");

        } else if (keyCode == 37 && !snake.getDirection().equals("right")) {
            snake.setDirection("left");

        } else if (keyCode == 38 && !snake.getDirection().equals("down")) {
            snake.setDirection("up");

        } else if (keyCode == 40 && !snake.getDirection().equals("up")) {
            snake.setDirection("down");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

    /**
     * Redraws the screen after an action
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
