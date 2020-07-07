package com.javaproject.employeerequest.exception;

public class WorkRegisterException extends Exception {
    public WorkRegisterException() {
        super();
    }

    public WorkRegisterException(String message) {
        super(message);
    }

    public WorkRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkRegisterException(Throwable cause) {
        super(cause);
    }
}
