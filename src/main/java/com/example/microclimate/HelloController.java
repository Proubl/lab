package com.example.microclimate;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class HelloController {
    @FXML
    private Label tickLabel;
    @FXML
    private TextField tempIntervalField;
    @FXML
    private TextField humidityIntervalField;
    @FXML
    private TextField lightIntervalField;
    @FXML
    private TextField ventilationIntervalField;
    @FXML
    private TextField lightingIntervalField;
    @FXML
    private Button toggleTempButton;
    @FXML
    private Button toggleHumidityButton;
    @FXML
    private Button toggleLightButton;
    @FXML
    private Button toggleVentilationButton;
    @FXML
    private Button toggleLightingButton;

    private PulseServer pulseServer;
    private TaskScheduler taskScheduler;
    private TemperatureSensor tempSensor;
    private HumiditySensor humiditySensor;
    private LightSensor lightSensor;
    private VentilationSystem ventilationSystem;
    private LightingSystem lightingSystem;

    public HelloController() {
        pulseServer = new PulseServer();
        pulseServer.addObserver(() -> Platform.runLater(() -> tickLabel.setText("Тик: " + pulseServer.getTick())));
    }

    @FXML
    protected void onStartButtonClick(ActionEvent event) {
        taskScheduler = new TaskScheduler(pulseServer);
        taskScheduler.start();
    }

    @FXML
    protected void setTempInterval(ActionEvent event) {
        int interval = Integer.parseInt(tempIntervalField.getText());
        tempSensor.setTickInterval(interval);
        System.out.println("Интервал тиков для температуры установлен на " + interval);
    }

    @FXML
    protected void toggleTempSensor(ActionEvent event) {
        if (tempSensor.isEnabled()) {
            tempSensor.disable();
            toggleTempButton.setText("Включить датчик температуры");
        } else {
            tempSensor.enable();
            toggleTempButton.setText("Отключить датчик температуры");
        }
    }

    @FXML
    protected void setHumidityInterval(ActionEvent event) {
        int interval = Integer.parseInt(humidityIntervalField.getText());
        humiditySensor.setTickInterval(interval);
        System.out.println("Интервал тиков для влажности установлен на " + interval);
    }

    @FXML
    protected void toggleHumiditySensor(ActionEvent event) {
        if (humiditySensor.isEnabled()) {
            humiditySensor.disable();
            toggleHumidityButton.setText("Включить датчик влажности");
        } else {
            humiditySensor.enable();
            toggleHumidityButton.setText("Отключить датчик влажности");
        }
    }

    @FXML
    protected void setLightInterval(ActionEvent event) {
        int interval = Integer.parseInt(lightIntervalField.getText());
        lightSensor.setTickInterval(interval);
        System.out.println("Интервал тиков для освещенности установлен на " + interval);
    }

    @FXML
    protected void toggleLightSensor(ActionEvent event) {
        if (lightSensor.isEnabled()) {
            lightSensor.disable();
            toggleLightButton.setText("Включить датчик освещенности");
        } else {
            lightSensor.enable();
            toggleLightButton.setText("Отключить датчик освещенности");
        }
    }

    @FXML
    protected void setVentilationInterval(ActionEvent event) {
        int interval = Integer.parseInt(ventilationIntervalField.getText());
        ventilationSystem.setTickInterval(interval);
        System.out.println("Интервал тиков для вентиляции установлен на " + interval);
    }

    @FXML
    protected void toggleVentilationSystem(ActionEvent event) {
        if (ventilationSystem.isEnabled()) {
            ventilationSystem.disable();
            toggleVentilationButton.setText("Включить систему вентиляции");
        } else {
            ventilationSystem.enable();
            toggleVentilationButton.setText("Отключить систему вентиляции");
        }
    }

    @FXML
    protected void setLightingInterval(ActionEvent event) {
        int interval = Integer.parseInt(lightingIntervalField.getText());
        lightingSystem.setTickInterval(interval);
        System.out.println("Интервал тиков для освещения установлен на " + interval);
    }

    @FXML
    protected void toggleLightingSystem(ActionEvent event) {
        if (lightingSystem.isEnabled()) {
            lightingSystem.disable();
            toggleLightingButton.setText("Включить систему освещения");
        } else {
            lightingSystem.enable();
            toggleLightingButton.setText("Отключить систему освещения");
        }
    }

    public void initialize() {
        // Инициализируем датчики
        SensorBuilder sensorBuilder = new SensorBuilder()
                .setTickInterval(3)
                .setPulseServer(pulseServer);

        tempSensor = sensorBuilder.buildTemperatureSensor();
        sensorBuilder.setTickInterval(6);
        humiditySensor = sensorBuilder.buildHumiditySensor();
        sensorBuilder.setTickInterval(5);
        lightSensor = sensorBuilder.buildLightSensor();
        ventilationSystem = sensorBuilder.buildVentilationSystem();
        lightingSystem = sensorBuilder.buildLightingSystem();
    }
}
