package com.example.microclimate;

public class SensorBuilder {
    private int tickInterval;
    private PulseServer pulseServer;

    public SensorBuilder setTickInterval(int tickInterval) {
        this.tickInterval = tickInterval;
        return this;
    }

    public SensorBuilder setPulseServer(PulseServer pulseServer) {
        this.pulseServer = pulseServer;
        return this;
    }

    public TemperatureSensor buildTemperatureSensor() {
        return new TemperatureSensor(tickInterval, pulseServer);
    }

    public HumiditySensor buildHumiditySensor() {
        return new HumiditySensor(tickInterval, pulseServer);
    }

    public LightSensor buildLightSensor() {
        return new LightSensor(tickInterval, pulseServer);
    }

    public VentilationSystem buildVentilationSystem() {
        return new VentilationSystem(tickInterval, pulseServer);
    }

    public LightingSystem buildLightingSystem() {
        return new LightingSystem(tickInterval, pulseServer);
    }
}
