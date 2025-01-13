package com.example.student_timer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {
    @FXML
    private Label scheduleLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label timeRemainingLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button loadFileButton;
    @FXML
    private TextField intervalTextField;

    private NotificationService notificationService;

    @FXML
    public void initialize() {

    }

    @FXML
    public void loadScheduleFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try {
                List<Schedule> scheduleList = ScheduleReader.readSchedule(file.getAbsolutePath());
                notificationService = new NotificationService(scheduleList, this::updateUI);
                System.out.println("Расписание загружено: " + file.getName());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка при загрузке расписания.");
            }
        }
    }

    private void updateUI(String schedule, String time, String timeRemaining) {
        scheduleLabel.setText("Следующая пара: " + schedule);
        timeLabel.setText("Время: " + time);
        timeRemainingLabel.setText(timeRemaining);
    }


    @FXML
    public void startNotifications() {
        if (notificationService != null) {
            notificationService.startNotifications();
        }
    }

    @FXML
    public void stopNotifications() {
        if (notificationService != null) {
            notificationService.stopNotifications();
            scheduleLabel.setText("Следующая пара: Не определена");
            timeLabel.setText("Время: 00:00-00:00");
            timeRemainingLabel.setText ("До начала: 0 минут");
        }
    }


    @FXML
    public void setNotificationInterval() {
        try {
            int interval = Integer.parseInt(intervalTextField.getText());

            if (interval > 0) {
                if (notificationService != null) {
                    System.out.println("Интервал установлен на: " + interval + " минут(ы).");

                    notificationService.setNotificationInterval(interval);
                }
            } else {
                System.out.println("Ошибка: интервал должен быть больше 0.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: интервал должен быть числом.");
        }
    }
}


