package com.example.student_timer;

import java.time.LocalTime;

public class Schedule {
    private String subject;
    private LocalTime startTime;
    private LocalTime endTime;

    public Schedule(String subject, LocalTime startTime, LocalTime endTime) {
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getSubject() {
        return subject;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return subject + " (" + startTime + " - " + endTime + ")";
    }
}
