package com.stefanpascu.pao.model;

import com.stefanpascu.pao.model.enums.Gender;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private Gender gender;
    private Date birthDate;

    public Person(String firstName, String lastName, Gender gender, Date birthDate) {
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

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                '}';
    }

}
