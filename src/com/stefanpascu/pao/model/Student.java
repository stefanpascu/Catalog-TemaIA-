package com.stefanpascu.pao.model;

import com.stefanpascu.pao.model.enums.Gender;

import java.util.Date;

public class Student extends Person{
    private Date enrollmentDate;

    public Student(String firstName, String lastName, Gender gender, Date birthDate, Date enrollmentDate) {
        super(firstName, lastName, gender, birthDate);
        this.enrollmentDate = enrollmentDate;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
}
