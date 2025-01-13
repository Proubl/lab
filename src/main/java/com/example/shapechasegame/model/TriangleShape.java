package com.example.shapechasegame.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TriangleShape extends ShapeModel {

    public TriangleShape(double x, double y, Color color, double size) {
        super(x, y, color, size);
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(
                0.0, 0.0,
                size, size * 2,
                -size, size * 2
        );
        polygon.setFill(color);
        shape = polygon;
        shape.setTranslateX(x);
        shape.setTranslateY(y);
    }

    private static final double MAX_VELOCITY = 10.0;

    @Override
    public void move() {
        double newX = shape.getTranslateX() + velocityX;
        double newY = shape.getTranslateY() + velocityY;

        if (newX - size < 0 || newX + size > 800) {
            velocityX = -velocityX;
        }
        if (newY < 0 || newY + size * 2 > 600) {
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
