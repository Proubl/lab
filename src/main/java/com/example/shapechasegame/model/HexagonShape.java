package com.example.shapechasegame.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexagonShape extends ShapeModel {

    public HexagonShape(double x, double y, Color color, double size) {
        super(x, y, color, size);
        Polygon polygon = new Polygon();
        double centerX = 0;
        double centerY = 0;
        double r = size;
        for (int i = 0; i < 6; i++) {
            double theta = Math.toRadians(60 * i);
            polygon.getPoints().add(centerX + r * Math.cos(theta));
            polygon.getPoints().add(centerY + r * Math.sin(theta));
        }
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
        if (newY - size < 0 || newY + size > 600) {
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
