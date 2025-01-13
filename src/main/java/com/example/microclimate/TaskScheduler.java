package com.example.microclimate;

import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduler {
    private Timer timer = new Timer();
    private PulseServer pulseServer;

    public TaskScheduler(PulseServer pulseServer) {
        this.pulseServer = pulseServer;
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pulseServer.pulse();
            }
        }, 0, 1000); // pulse every second
    }

    public void stop() {
        timer.cancel();
    }
}
