package com.example.microclimate;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DataDisplay extends VBox implements Observer {
    private PulseServer pulseServer;
    private Label tickLabel;

    public DataDisplay(PulseServer pulseServer) {
        this.pulseServer = pulseServer;
        this.pulseServer.addObserver(this);
        tickLabel = new Label("Tick: 0");
        getChildren().add(tickLabel);
    }

    @Override
    public void update() {
        tickLabel.setText("Tick: " + pulseServer.getTick());
    }
}
