package edu.ship.engr.presentation;

public class MessageClock {
    private int clock;

    public MessageClock () {
        this.clock = 0;
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public int getCurrentClock() {
        return clock;
    }

    public int getUpdatedClock() {
        return clock++;
    }
}
