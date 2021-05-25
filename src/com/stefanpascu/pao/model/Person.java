package com.stefanpascu.pao.model;

import com.stefanpascu.pao.model.enums.Gender;

import java.util.Date;
import java.util.UUID;

public class Person {
    private String id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthDate;

    public Person(String id, String firstName, String lastName, Gender gender, Date birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public Person(String firstName, String lastName, Gender gender, Date birthDate) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                '}';
    }
}
