package com.example.microclimate;

public class MicroClimateControlTest {
    public static void main(String[] args) {
        PulseServer pulseServer = new PulseServer();
        SensorBuilder sensorBuilder = new SensorBuilder()
                .setTickInterval(5)
                .setPulseServer(pulseServer);

        TemperatureSensor tempSensor = sensorBuilder.buildTemperatureSensor();
        HumiditySensor humiditySensor = sensorBuilder.buildHumiditySensor();
        LightSensor lightSensor = sensorBuilder.buildLightSensor();
        VentilationSystem ventilationSystem = sensorBuilder.buildVentilationSystem();
        LightingSystem lightingSystem = sensorBuilder.buildLightingSystem();

        TaskScheduler taskScheduler = new SystemBuilder()
                .setPulseServer(pulseServer)
                .buildTaskScheduler();
        taskScheduler.start();

        DataDisplay dataDisplay = new DataDisplay(pulseServer);
    }
}
