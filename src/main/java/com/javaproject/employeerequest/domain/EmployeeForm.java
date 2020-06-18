package com.javaproject.employeerequest.domain;

import com.javaproject.employeerequest.domain.data.EducationData;
import com.javaproject.employeerequest.domain.data.EmployeeData;
import com.javaproject.employeerequest.domain.data.PersonData;
import com.javaproject.employeerequest.domain.data.PreviousEmployerData;
import com.javaproject.employeerequest.domain.data.components.FormStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeForm {
    private long employeeFormId;
    private FormStatus status;
    private LocalDateTime formDate;
    private PersonData personData;
    private EmployeeData employeeData;
    private List<PreviousEmployerData> previousEmployers;
    private EducationData education;


    public long getEmployeeFormId() {
        return employeeFormId;
    }

    public void setEmployeeFormId(long employeeFormId) {
        this.employeeFormId = employeeFormId;
    }

    public FormStatus getStatus() {
        return status;
    }

    public void setStatus(FormStatus status) {
        this.status = status;
    }

    public LocalDateTime getFormDate() {
        return formDate;
    }

    public void setFormDate(LocalDateTime formDate) {
        this.formDate = formDate;
    }

    public EducationData getEducation() {
        return education;
    }

    public void setEducation(EducationData education) {
        this.education = education;
    }

    public EmployeeData getEmployeeData() {
        return employeeData;
    }

    public void setEmployeeData(EmployeeData employeeData) {
        this.employeeData = employeeData;
    }

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    public List<PreviousEmployerData> getPreviousEmployers() {
        return previousEmployers;
    }

    public void addPreviousEmployers(PreviousEmployerData previousEmployer) {
        if (previousEmployers == null)
            previousEmployers = new ArrayList<>(4);
        previousEmployers.add(previousEmployer);
    }

}
