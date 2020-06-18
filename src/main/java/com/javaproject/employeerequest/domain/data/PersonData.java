package com.javaproject.employeerequest.domain.data;

import com.javaproject.employeerequest.domain.data.components.City;
import com.javaproject.employeerequest.domain.data.components.Mail;
import com.javaproject.employeerequest.domain.data.components.RelocateStatus;

import java.time.LocalDate;

public class PersonData {
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private City currentCity;
    private RelocateStatus relocateStatus;
    private String about;
    //TODO make mail Class MAil
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public RelocateStatus getRelocateStatus() {
        return relocateStatus;
    }

    public void setRelocateStatus(RelocateStatus relocateStatus) {
        this.relocateStatus = relocateStatus;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
