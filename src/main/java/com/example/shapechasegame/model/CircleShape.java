package com.example.shapechasegame.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleShape extends ShapeModel {

    private static final double MAX_VELOCITY = 10.0;

    public CircleShape(double x, double y, Color color, double size) {
        super(x, y, color, size);
        shape = new Circle(size);
        shape.setFill(color);
        shape.setTranslateX(x);
        shape.setTranslateY(y);
    }

    @Override
    public void move() {
        double newX = shape.getTranslateX() + velocityX;
        double newY = shape.getTranslateY() + velocityY;

        if (newX < 0 || newX > 800 - size) {
            velocityX = -velocityX;
        }
        if (newY < 0 || newY > 600 - size) {
            velocityY = -velocityY;
        }

        // Ограничение скорости
        if (Math.abs(velocityX) > MAX_VELOCITY) {
            velocityX = Math.signum(velocityX) * MAX_VELOCITY;
        }
        if (Math.abs(velocityY) > MAX_VELOCITY) {
            velocityY = Math.signum(velocityY) * MAX_VELOCITY;
        }

        shape.setTranslateX(shape.getTranslateX() + velocityX);
        shape.setTranslateY(shape.getTranslateY() + velocityY);
    }
}
