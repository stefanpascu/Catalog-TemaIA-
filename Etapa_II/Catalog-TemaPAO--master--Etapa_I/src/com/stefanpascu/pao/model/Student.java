package com.stefanpascu.pao.model;

import com.stefanpascu.pao.model.enums.Gender;

import java.util.Date;

public class Student extends Person{
    private String classroomId;

    public Student(String id, String firstName, String lastName, Gender gender, Date birthDate, String classroomId) {
        super(id, firstName, lastName, gender, birthDate);
        this.classroomId = classroomId;
    }

    public Student(String firstName, String lastName, Gender gender, Date birthDate, String classroomId) {
        super(firstName, lastName, gender, birthDate);
        this.classroomId = classroomId;
    }

    public String getClassroomId() {
        return classroomId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "classroomId='" + classroomId + '\'' +
                "} " + super.toString();
    }
}
