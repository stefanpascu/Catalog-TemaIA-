package com.stefanpascu.pao.model;

import com.stefanpascu.pao.model.enums.SubjectName;

public class Subject {

    private String id;
    private SubjectName name;

    public Subject(String id, SubjectName name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public SubjectName getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
