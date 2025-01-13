package com.example.microclimate;

public class TemperatureSensor extends Sensor {
    public TemperatureSensor(int tickInterval, PulseServer pulseServer) {
        super(tickInterval, pulseServer);
    }

    @Override
    protected void measure() {
        System.out.println("Измерение температуры на такте " + pulseServer.getTick());
    }
}
