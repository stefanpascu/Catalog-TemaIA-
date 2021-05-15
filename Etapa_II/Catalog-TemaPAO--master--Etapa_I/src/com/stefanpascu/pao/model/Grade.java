package com.stefanpascu.pao.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Grade {
    private String id;
    private int value;
    private Date date;
    private String subjectId;
    private String studentId;

    public Grade(String id, int value, Date date, String subjectId, String studentId) {
        if (value < 1 || value > 10) {
            throw new RuntimeException("The grade's value must be between 1 and 10.");
        }
        this.id = id;
        this.value = value;
        this.date = date;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }

    public Grade(int value, Date date, String subjectId, String studentId) {
        this(UUID.randomUUID().toString(), value, date, subjectId, studentId);
    }

    public int getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "value=" + value +
                ", date=" + date +
                ", subjectId='" + subjectId + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
