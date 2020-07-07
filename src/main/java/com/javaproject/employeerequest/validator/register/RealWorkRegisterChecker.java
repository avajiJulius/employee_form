package com.javaproject.employeerequest.validator.register;

import com.javaproject.employeerequest.domain.data.LastWorkData;
import com.javaproject.employeerequest.domain.data.PersonData;
import com.javaproject.employeerequest.domain.register.WorkRegisterResponse;
import com.javaproject.employeerequest.exception.WorkRegisterException;

public class RealWorkRegisterChecker implements WorkRegisterChecker{
    @Override
    public WorkRegisterResponse checkPerson(PersonData pd, LastWorkData lwd) throws WorkRegisterException {



        return null;
    }
}
