package com.example.shapechasegame.controller;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AnimalController {
    private Circle player;
    private double velocity = 5;

    public AnimalController(Pane root) {
        player = new Circle(20, Color.BLUE);
        player.setTranslateX(400);
        player.setTranslateY(300);
        root.getChildren().add(player);
    }

    private void handleMovement(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                player.setTranslateY(player.getTranslateY() - 5);
                break;
            case DOWN:
                player.setTranslateY(player.getTranslateY() + 5);
                break;
            case LEFT:
                player.setTranslateX(player.getTranslateX() - 5);
                break;
            case RIGHT:
                player.setTranslateX(player.getTranslateX() + 5);
                break;
        }
    }

    public Circle getPlayer() {
        return player;
    }
}
