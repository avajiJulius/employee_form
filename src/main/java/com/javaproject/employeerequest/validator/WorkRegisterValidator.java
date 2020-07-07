package com.javaproject.employeerequest.validator;

import com.javaproject.employeerequest.domain.EmployeeForm;
import com.javaproject.employeerequest.domain.data.LastWorkData;
import com.javaproject.employeerequest.domain.data.PersonData;
import com.javaproject.employeerequest.domain.register.AnswerWorkRegister;
import com.javaproject.employeerequest.domain.register.AnswerWorkRegisterItem;
import com.javaproject.employeerequest.exception.WorkRegisterException;
import com.javaproject.employeerequest.validator.register.RealWorkRegisterChecker;
import com.javaproject.employeerequest.validator.register.WorkRegisterChecker;

import java.util.List;

public class WorkRegisterValidator {
    public static final String IN_CODE = "NO_WR";

    private WorkRegisterChecker personChecker;

    public WorkRegisterValidator() {
        personChecker = new RealWorkRegisterChecker();
    }

    public AnswerWorkRegister checkWorkRegister(EmployeeForm ef) {
        AnswerWorkRegister awr = new AnswerWorkRegister();

        awr.addItem(checkPerson(ef.getPersonData(), ef.getLastWork()));

        return awr;
    }

    private AnswerWorkRegisterItem checkPerson(PersonData personData, LastWorkData lastWork) {
        AnswerWorkRegisterItem.WorkStatus status = null;
        AnswerWorkRegisterItem.WorkError error = null;

        try{

        } catch(WorkRegisterException ex) {
            status = AnswerWorkRegisterItem.WorkStatus.ERROR;
            error = new AnswerWorkRegisterItem.WorkError(ex.getCode(), ex.getMessage());
        } catch (Exception ex) {
            status = AnswerWorkRegisterItem.WorkStatus.ERROR;
            error = new AnswerWorkRegisterItem.WorkError(IN_CODE, ex.getMessage());
        }

        AnswerWorkRegisterItem awr = new AnswerWorkRegisterItem(status, personData, lastWork, error);

        return awr;
    }
}
