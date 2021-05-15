package com.stefanpascu.pao.model;

import java.util.List;

public class Classroom {

    private String id;
    private String name;

    public Classroom(String name) {
        this.name = name;
    }

    public Classroom(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
