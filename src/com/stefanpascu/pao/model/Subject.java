package com.stefanpascu.pao.model;

import com.stefanpascu.pao.model.enums.SubjectName;

public class Subject {

    private SubjectName name;
    private Teacher teacher;

    public Subject(SubjectName name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public SubjectName getName() {
        return name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name=" + name +
                ", teacher=" + teacher +
                '}';
    }
}
