package com.example.microclimate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;

public class Main extends Application {
    private PulseServer pulseServer;
    private TaskScheduler taskScheduler;

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/microclimate/layout.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Управление микроклиматом");
            primaryStage.setScene(scene);
            primaryStage.show();

            // Обработчик закрытия окна
            primaryStage.setOnCloseRequest(event -> {
                if (taskScheduler != null) {
                    taskScheduler.stop();
                }
                Platform.exit();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
