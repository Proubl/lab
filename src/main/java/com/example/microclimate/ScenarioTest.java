package com.example.microclimate;

public class ScenarioTest {
    public static void main(String[] args) {
        PulseServer pulseServer = new PulseServer();
        SensorBuilder sensorBuilder = new SensorBuilder()
                .setTickInterval(3)
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

        // Отключим и подключим датчики через 10 секунд (примерно 10 тиков)
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println("Disabling Temperature and Humidity Sensors...");
                tempSensor.disable();
                humiditySensor.disable();

                new java.util.Timer().schedule(new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("Enabling Temperature and Humidity Sensors...");
                        tempSensor.enable();
                        humiditySensor.enable();
                    }
                }, 10000); // Повторное включение через 10 секунд
            }
        }, 10000); // Отключение через 10 секунд
    }
}
