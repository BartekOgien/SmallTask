package com.reader;

import java.time.LocalDate;

public class User {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String phoneNumber;

    public User(String name, String surname, LocalDate dateOfBirth, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
