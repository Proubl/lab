package com.example.shapechasegame.view;

import com.example.shapechasegame.controller.GameSettings;
import com.example.shapechasegame.model.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GameView {
    private Pane root;
    private List<ShapeModel> shapes;
    private int score; // Переменная для отслеживания очков
    private ShapeCreationStrategy currentStrategy; // Текущая стратегия создания фигур

    public GameView() {
        root = new Pane();
        shapes = new ArrayList<>();
        score = 0; // Начинаем с 0 очков
        currentStrategy = new CircleCreationStrategy(); // Начинаем с кругов
        initializeShapes();
    }

    private void initializeShapes() {
        for (int i = 0; i < 4; i++) {
            addNewShape();
        }
    }

    public Pane getRoot() {
        return root;
    }

    public List<ShapeModel> getShapes() {
        return shapes;
    }

    public void update() {
        for (ShapeModel shape : shapes) {
            shape.move();
        }
    }

    public void removeShape(ShapeModel shape) {
        shapes.remove(shape);
        root.getChildren().remove(shape.getShape());
        increaseScore(); // Увеличиваем очки при удалении фигуры
    }

    private void increaseScore() {
        score++;
        updateStrategy(); // Обновляем стратегию в зависимости от очков
    }

    private void updateStrategy() {
        // Меняем стратегию в зависимости от очков
        if (score < 5) {
            currentStrategy = new CircleCreationStrategy(); // Круги для 0-4 очков
        } else if (score < 10) {
            currentStrategy = new SquareCreationStrategy(); // Квадраты для 5-9 очков
        } else if (score < 15) {
            currentStrategy = new TriangleCreationStrategy(); // Треугольники для 10-14 очков
        } else {
            // Циклическое изменение фигур каждые 5 очков
            currentStrategy = (score / 5) % 3 == 0 ? new CircleCreationStrategy() :
                    (score / 5) % 3 == 1 ? new SquareCreationStrategy() :
                            new TriangleCreationStrategy();
        }

        // Обновляем все фигуры на новую
        updateAllShapes();
    }

    private void updateAllShapes() {
        for (int i = 0; i < shapes.size(); i++) {
            ShapeModel oldShape = shapes.get(i);
            double x = oldShape.getX(); // Сохраняем текущую координату X
            double y = oldShape.getY(); // Сохраняем текущую координату Y
            double size = oldShape.getSize(); // Сохраняем текущий размер фигуры

            // Создаем новую фигуру с текущей стратегией, но сохраняем текущие координаты
            ShapeModel newShape = currentStrategy.createShape(x, y, size, GameSettings.getRandomColor());
            shapes.set(i, newShape); // Обновляем список фигур
            root.getChildren().set(i, newShape.getShape()); // Обновляем отображение на панели
        }
    }

    public void addNewShape() {
        double size = 20 + (int) (Math.random() * 30); // Случайный размер от 20 до 50
        double x = size + Math.random() * (800 - 2 * size);
        double y = size + Math.random() * (600 - 2 * size);
        Color color = GameSettings.getRandomColor();

        ShapeModel newShape = currentStrategy.createShape(x, y, size, color);
        shapes.add(newShape);
        newShape.getShape().getProperties().put("shapeModel", newShape);
        root.getChildren().add(newShape.getShape());
    }

    // Интерфейс для стратегии
    private interface ShapeCreationStrategy {
        ShapeModel createShape(double x, double y, double size, Color color);
    }

    // Стратегия для создания кругов
    private class CircleCreationStrategy implements ShapeCreationStrategy {
        public ShapeModel createShape(double x, double y, double size, Color color) {
            return new CircleShape(x, y, color, size);
        }
    }

    // Стратегия для создания квадратов
    private class SquareCreationStrategy implements ShapeCreationStrategy {
        public ShapeModel createShape(double x, double y, double size, Color color) {
            return new SquareShape(x, y, color, size);
        }
    }

    // Стратегия для создания треугольников
    private class TriangleCreationStrategy implements ShapeCreationStrategy {
        public ShapeModel createShape(double x, double y, double size, Color color) {
            return new TriangleShape(x, y, color, size);
        }
    }
}
