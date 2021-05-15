package com.stefanpascu.pao.model;

import com.stefanpascu.pao.model.enums.Gender;

import java.util.Date;

public class Teacher extends Person{
    private Date employmentDate;
    private String subjectId;

    public Teacher(String id, String firstName, String lastName, Gender gender, Date birthDate, String subjectId) {
        super(id, firstName, lastName, gender, birthDate);
        this.subjectId = subjectId;
    }

    public Teacher(String firstName, String lastName, Gender gender, Date birthDate, String subjectId) {
        super(firstName, lastName, gender, birthDate);
        this.subjectId = subjectId;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "employmentDate=" + employmentDate +
                '}';
    }
}
