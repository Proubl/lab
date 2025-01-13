package com.example.microclimate;

public class SystemBuilder {
    private PulseServer pulseServer;

    public SystemBuilder setPulseServer(PulseServer pulseServer) {
        this.pulseServer = pulseServer;
        return this;
    }

    public PulseServer buildPulseServer() {
        return pulseServer;
    }

    public TaskScheduler buildTaskScheduler() {
        return new TaskScheduler(pulseServer);
    }
}
