package com.example.microclimate;

public abstract class Builder {
    protected PulseServer pulseServer;

    public void setPulseServer(PulseServer pulseServer) {
        this.pulseServer = pulseServer;
    }

    public abstract void buildSensors();
    public abstract void buildSystems();
}
