package com.example.shapechasegame.controller;

import javafx.scene.paint.Color; // Добавляем импорт для Color

public class GameSettings {
    private static String difficulty = "Средний";
    private static String colorScheme = "Светлая";
    private static String movementType = "Плавное перемещение"; // Тип перемещения

    private static double speedMultiplier;

    static {
        updateSpeedMultiplier();
    }

    public static void setDifficulty(String difficulty) {
        GameSettings.difficulty = difficulty;
        updateSpeedMultiplier();
    }

    public static void setColorScheme(String colorScheme) {
        GameSettings.colorScheme = colorScheme;
    }

    public static void setMovementType(String movementType) {
        GameSettings.movementType = movementType;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static String getColorScheme() {
        return colorScheme;
    }

    public static String getMovementType() {
        return movementType;
    }

    public static double getSpeedMultiplier() {
        return speedMultiplier;
    }

    private static void updateSpeedMultiplier() {
        switch (difficulty) {
            case "Легкий":
                speedMultiplier = 0.5;
                break;
            case "Средний":
                speedMultiplier = 1.0;
                break;
            case "Сложный":
                speedMultiplier = 1.5;
                break;
        }
    }

    public static Color getRandomColor() {
        double r = Math.random();
        double g = Math.random();
        double b = Math.random();

        if (colorScheme.equals("Темная")) {
            return Color.color(r * 0.5, g * 0.5, b * 0.5); // Темные цвета
        } else {
            return Color.color(r * 0.5 + 0.5, g * 0.5 + 0.5, b * 0.5 + 0.5); // Яркие цвета
        }
    }
}
