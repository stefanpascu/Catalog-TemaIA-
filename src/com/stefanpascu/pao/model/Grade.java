package com.stefanpascu.pao.model;

import java.util.Date;

public class Grade {
    private int value;
    private Date date;
    private Subject subject;

    public Grade(int value, Date date, Subject subject) {
        if (value < 1 || value > 10) {
            throw new RuntimeException("The grade's value must be between 1 and 10.");
        }
        this.value = value;
        this.date = date;
        this.subject = subject;
    }

    public int getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public Subject getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "value=" + value +
                ", date=" + date +
                ", subject=" + subject +
                '}';
    }
}
