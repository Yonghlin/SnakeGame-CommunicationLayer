package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class InitializeGame {
    private boolean startGame = false;

    public InitializeGame(boolean startGame) {
        startGame = startGame;
    }

    public InitializeGame(LinkedHashMap<String, Object> p) {
        startGame = (boolean) p.get("startGame");
    }

    @Override
    public String toString()
    {
        return "Game Started!";
    }

    public boolean getStartGame() {
        return startGame;
    }
}
