package com.example.student_timer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ScheduleReader {
    public static List<Schedule> readSchedule(String filePath) throws IOException {
        List<Schedule> scheduleList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");

                if (parts.length == 3) {
                    String subject = parts[0].trim();
                    String startTimeStr = parts[1].trim();
                    String endTimeStr = parts[2].trim();

                    try {

                        LocalTime startTime = LocalTime.parse(startTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
                        LocalTime endTime = LocalTime.parse(endTimeStr, DateTimeFormatter.ofPattern("HH:mm"));

                        scheduleList.add(new Schedule(subject, startTime, endTime));
                    } catch (Exception e) {
                        System.out.println("Ошибка при парсинге времени: " + e.getMessage());
                    }
                } else {
                    System.out.println("Неверный формат строки: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return scheduleList;
    }
}
