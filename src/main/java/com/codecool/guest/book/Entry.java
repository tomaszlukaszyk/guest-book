package com.codecool.guest.book;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Entry {
    private int id;
    private String message;
    private String name;
    private LocalDateTime date;

    public Entry() {
    }

    public Entry(String message, String name, LocalDateTime date) {
        this.message = message;
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDateAsString() {
        return this.date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
