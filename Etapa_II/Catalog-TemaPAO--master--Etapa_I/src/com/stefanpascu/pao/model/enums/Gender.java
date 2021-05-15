package com.stefanpascu.pao.model.enums;

public enum Gender {
    MALE,
    FEMALE,
    OTHER;

    public static Gender get(String gender) {
        for (Gender g : Gender.values()) {
            if (g.name().equals(gender)) {
                return g;
            }
        }

        throw new RuntimeException("Unknown gender value");
    }
}
