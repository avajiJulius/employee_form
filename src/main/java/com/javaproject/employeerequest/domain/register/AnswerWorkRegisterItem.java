package com.javaproject.employeerequest.domain.register;

import com.javaproject.employeerequest.domain.data.LastWorkData;
import com.javaproject.employeerequest.domain.data.PersonData;

public class AnswerWorkRegisterItem {
    public enum WorkStatus {
        YES, NO, ERROR
    }

    public static class WorkError {
        private String code;
        private String text;

        public WorkError(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getCode() {
            return code;
        }

        public String getText() {
            return text;
        }
    }

    private WorkStatus status;
    private PersonData person;
    private LastWorkData lastWork;
    private WorkError error;

    public AnswerWorkRegisterItem(WorkStatus status, PersonData person, LastWorkData lastWork) {
        this.status = status;
        this.person = person;
        this.lastWork = lastWork;
    }

    public AnswerWorkRegisterItem(WorkStatus status, PersonData person, LastWorkData lastWork, WorkError error) {
        this.status = status;
        this.person = person;
        this.lastWork = lastWork;
        this.error = error;
    }

    public WorkStatus getStatus() {
        return status;
    }

    public PersonData getPerson() {
        return person;
    }

    public LastWorkData getLastWork() {
        return lastWork;
    }

    public WorkError getError() {
        return error;
    }
}
