package com.example.shapechasegame.controller;

import com.example.shapechasegame.Main;
import com.example.shapechasegame.model.ShapeModel;
import com.example.shapechasegame.view.GameView;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private ImageView backgroundImage;

    @FXML
    private Pane gamePane;

    @FXML
    private Circle player;

    @FXML
    private Label scoreLabel;

    private GameView gameView;
    private AnimationTimer timer;
    private int score = 0;

    private double targetX;
    private double targetY;
    private static final double SPEED = 2.0; // скорость перемещения

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameView = new GameView();
        gamePane.getChildren().add(gameView.getRoot());

        backgroundImage.setImage(new Image(getClass().getResource("/com/example/shapechasegame/view/images/background.jpg").toString()));
        player.setTranslateX(400);
        player.setTranslateY(300);

        targetX = player.getTranslateX();
        targetY = player.getTranslateY();

        gamePane.addEventFilter(MouseEvent.MOUSE_CLICKED, this::handleMouseInput);

        startGame();
    }

    @FXML
    private void handleMouseInput(MouseEvent event) {
        if (GameSettings.getMovementType().equals("Телепортация")) {
            player.setTranslateX(event.getX());
            player.setTranslateY(event.getY());
        } else {
            targetX = event.getX();
            targetY = event.getY();
        }
    }

    private void startGame() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
                checkCollisions();
                if (!GameSettings.getMovementType().equals("Телепортация")) {
                    movePlayer();
                }
            }
        };
        timer.start();
    }

    private void updateGame() {
        gameView.update();
    }

    private void checkCollisions() {
        for (ShapeModel shape : gameView.getShapes()) {
            if (player.getBoundsInParent().intersects(shape.getShape().getBoundsInParent())) {
                // Успешная поимка фигуры
                gameView.removeShape(shape);
                gameView.addNewShape();
                score++;
                updateScoreLabel();
                break;
            }
        }
    }

    private void movePlayer() {
        double deltaX = targetX - player.getTranslateX();
        double deltaY = targetY - player.getTranslateY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        if (distance > SPEED) {
            player.setTranslateX(player.getTranslateX() + (deltaX / distance) * SPEED);
            player.setTranslateY(player.getTranslateY() + (deltaY / distance) * SPEED);
        } else {
            player.setTranslateX(targetX);
            player.setTranslateY(targetY);
        }
    }

    public void setUpKeyHandlers(Stage stage) {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> handleMovement(event.getCode()));
    }

    private void handleMovement(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                player.setTranslateY(Math.max(player.getTranslateY() - 5, 0));
                break;
            case DOWN:
                player.setTranslateY(Math.min(player.getTranslateY() + 5, 580));
                break;
            case LEFT:
                player.setTranslateX(Math.max(player.getTranslateX() - 5, 0));
                break;
            case RIGHT:
                player.setTranslateX(Math.min(player.getTranslateX() + 5, 780));
                break;
        }
    }

    @FXML
    private void openSettings() {
        try {
            Main mainApp = new Main();
            mainApp.showSettings((Stage) gamePane.getScene().getWindow());
            updateShapeSpeeds();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateShapeSpeeds() {
        double speedMultiplier = GameSettings.getSpeedMultiplier();
        for (ShapeModel shape : gameView.getShapes()) {
            double newVelocityX = shape.getVelocityX() / speedMultiplier;
            double newVelocityY = shape.getVelocityY() / speedMultiplier;
            shape.setVelocity(newVelocityX, newVelocityY);
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Счет: " + score);
    }
}
