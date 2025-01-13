package com.example.microclimate;

public class PulseServerTest {
    public static void main(String[] args) {
        PulseServer pulseServer = new PulseServer();
        pulseServer.pulse();
        System.out.println("Current tick: " + pulseServer.getTick());
    }
}
