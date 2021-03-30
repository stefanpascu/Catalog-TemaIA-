package com.stefanpascu.pao.model;

import com.stefanpascu.pao.model.enums.Gender;

import java.util.Date;

public class Teacher extends Person{
    private Date employmentDate;

    public Teacher(String firstName, String lastName, Gender gender, Date birthDate, Date employmentDate) {
        super(firstName, lastName, gender, birthDate);
        this.employmentDate = employmentDate;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "employmentDate=" + employmentDate +
                '}';
    }
}
