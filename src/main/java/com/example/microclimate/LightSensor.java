package com.example.microclimate;

public class LightSensor extends Sensor {
    public LightSensor(int tickInterval, PulseServer pulseServer) {
        super(tickInterval, pulseServer);
    }

    @Override
    protected void measure() {
        System.out.println("Управление системой освещения на такте " + pulseServer.getTick());
    }
}
