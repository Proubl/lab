package com.example.student_timer;

import javafx.application.Platform;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationService {
    private final List<Schedule> scheduleList;
    private Timer timer;
    private final UpdateCallback updateCallback;

    public NotificationService(List<Schedule> scheduleList, UpdateCallback updateCallback) {
        this.scheduleList = scheduleList;
        this.updateCallback = updateCallback;
    }

    public void startNotifications() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkScheduleAndUpdateUI();
            }
        }, 0, 60000);
    }

    public void stopNotifications() {
        if (timer != null) {
            System.out.println("Останавливаем уведомления...");
            timer.cancel();
            timer = null;
        } else {
            System.out.println("Таймер уже остановлен или не был запущен.");
        }
    }

    public void setNotificationInterval(int intervalMinutes) {
        stopNotifications();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkScheduleAndUpdateUI();
            }
        }, 0, intervalMinutes * 60000);
    }

    private void checkScheduleAndUpdateUI() {
        ZonedDateTime moscowTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        LocalTime now = moscowTime.toLocalTime();

        boolean pairFound = false;

        for (Schedule schedule : scheduleList) {
            if (now.isBefore(schedule.getStartTime())) {
                long minutesUntilStart = now.until(schedule.getStartTime(), java.time.temporal.ChronoUnit.MINUTES);

                Platform.runLater(() -> updateCallback.update(
                        schedule.getSubject(),
                        schedule.getStartTime() + " - " + schedule.getEndTime(),
                        minutesUntilStart + " минут до начала"
                ));
                pairFound = true;
                break;
            }
            else if (now.isBefore(schedule.getEndTime())) {
                long minutesUntilEnd = now.until(schedule.getEndTime(), java.time.temporal.ChronoUnit.MINUTES);

                Platform.runLater(() -> updateCallback.update(
                        schedule.getSubject(),
                        schedule.getStartTime() + " - " + schedule.getEndTime(),
                        minutesUntilEnd + " минут(ы) до конца"
                ));
                pairFound = true;
                break;
            }
        }

        if (!pairFound) {
            Platform.runLater(() -> updateCallback.update("Нет пар", "", ""));
        }
    }
}
