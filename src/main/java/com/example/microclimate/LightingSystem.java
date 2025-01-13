package com.example.microclimate;

public class LightingSystem extends Sensor {
    public LightingSystem(int tickInterval, PulseServer pulseServer) {
        super(tickInterval, pulseServer);
    }

    @Override
    protected void measure() {
        System.out.println("Регулировка освещения на такте " + pulseServer.getTick());
    }
}
