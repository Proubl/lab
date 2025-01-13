package com.example.microclimate;

public class VentilationSystem extends Sensor {
    public VentilationSystem(int tickInterval, PulseServer pulseServer) {
        super(tickInterval, pulseServer);
    }

    @Override
    protected void measure() {
        System.out.println("Регулировка вентиляции на такте " + pulseServer.getTick());
    }
}
