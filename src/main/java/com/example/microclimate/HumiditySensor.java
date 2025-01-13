package com.example.microclimate;

public class HumiditySensor extends Sensor {
    public HumiditySensor(int tickInterval, PulseServer pulseServer) {
        super(tickInterval, pulseServer);
    }

    @Override
    protected void measure() {
        System.out.println("Измерение влажности на такте " + pulseServer.getTick());
    }
}
