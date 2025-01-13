package com.example.microclimate;

public abstract class Sensor implements Observer {
    protected int tickInterval;
    protected PulseServer pulseServer;
    private boolean enabled = true;

    public Sensor(int tickInterval, PulseServer pulseServer) {
        this.tickInterval = tickInterval;
        this.pulseServer = pulseServer;
        pulseServer.addObserver(this);
    }

    public void disable() {
        enabled = false;
        pulseServer.removeObserver(this);
        System.out.println(getClass().getSimpleName() + " отключен.");
    }

    public void enable() {
        enabled = true;
        pulseServer.addObserver(this);
        System.out.println(getClass().getSimpleName() + " включен.");
    }

    public void setTickInterval(int tickInterval) {
        this.tickInterval = tickInterval;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void update() {
        if (enabled && pulseServer.getTick() % tickInterval == 0) {
            measure();
        }
    }

    protected abstract void measure();
}
