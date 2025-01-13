package com.example.shapechasegame;

import com.example.shapechasegame.controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/shapechasegame/view/GameView.fxml"));
        AnchorPane root = loader.load(); // Используем AnchorPane здесь
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Погоня за фигурами");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); // Установка невозможности изменения размера окна
        primaryStage.setOnShown(event -> {
            GameController controller = loader.getController();
            controller.setUpKeyHandlers(primaryStage);
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showSettings(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/shapechasegame/view/SettingsView.fxml"));
        AnchorPane settingsRoot = loader.load(); // Используем AnchorPane здесь

        Scene settingsScene = new Scene(settingsRoot, 400, 300);
        Stage settingsStage = new Stage();
        settingsStage.setTitle("Настройки");
        settingsStage.setScene(settingsScene);
        settingsStage.initOwner(primaryStage);
        settingsStage.setResizable(false); // Установка невозможности изменения размера окна
        settingsStage.show();
    }
}
