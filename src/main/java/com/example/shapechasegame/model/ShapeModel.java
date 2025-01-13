package com.example.shapechasegame.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import com.example.shapechasegame.controller.GameSettings;

import java.util.Random;

public abstract class ShapeModel {
    protected double x, y; // Координаты
    protected double velocityX, velocityY;
    protected Color color;
    protected double size;
    protected Shape shape;

    public ShapeModel(double x, double y, Color color, double size) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = size;
        Random random = new Random();
        double speedMultiplier = GameSettings.getSpeedMultiplier();
        this.velocityX = (random.nextDouble() - 0.5) * 4 * speedMultiplier;
        this.velocityY = (random.nextDouble() - 0.5) * 4 * speedMultiplier;
    }

    public abstract void move();

    public Shape getShape() {
        return shape;
    }

    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    // Добавляем методы для получения координат
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size; // Добавляем метод для получения размера
    }
}