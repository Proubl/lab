package com.example.microclimate;

import java.util.ArrayList;
import java.util.List;

public class PulseServer extends Subject {
    private int tick = 0;

    public void pulse() {
        tick++;
        notifyObservers();
    }

    public int getTick() {
        return tick;
    }
}
