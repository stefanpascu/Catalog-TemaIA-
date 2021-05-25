package com.stefanpascu.pao.model;

import java.util.Date;
import java.util.UUID;

public class Absence {

    private String id;
    private int value;
    private Date date;
    private String subjectId;
    private String studentId;

    public Absence(String id, int value, Date date, String subjectId, String studentId) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.subjectId = subjectId;
        this.studentId = studentId;
    }
    public Absence(int value, Date date, String subjectId, String studentId) {
        this(UUID.randomUUID().toString(), value, date, subjectId, studentId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Absence{" +
                "value=" + value +
                ", date=" + date +
                ", subjectId='" + subjectId + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
