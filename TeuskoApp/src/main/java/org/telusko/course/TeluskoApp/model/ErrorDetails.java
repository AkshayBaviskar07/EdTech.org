package org.telusko.course.TeluskoApp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ErrorDetails {
    private String message;

    private LocalDate date;
    private LocalTime time;

    public ErrorDetails(String message, LocalDate date, LocalTime time) {
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
