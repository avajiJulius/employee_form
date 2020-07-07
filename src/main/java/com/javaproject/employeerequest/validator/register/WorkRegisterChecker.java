package com.javaproject.employeerequest.validator.register;

import com.javaproject.employeerequest.domain.EmployeeForm;
import com.javaproject.employeerequest.domain.data.LastWorkData;
import com.javaproject.employeerequest.domain.data.PersonData;
import com.javaproject.employeerequest.domain.register.WorkRegisterResponse;
import com.javaproject.employeerequest.exception.WorkRegisterException;

public interface WorkRegisterChecker {
    WorkRegisterResponse checkPerson(PersonData pd, LastWorkData lwd) throws WorkRegisterException;
}
