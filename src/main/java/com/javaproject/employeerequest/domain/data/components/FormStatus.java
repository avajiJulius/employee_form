package com.javaproject.employeerequest.domain.data.components;


public enum FormStatus {
    UNCHECKED, CHECKED;

    public static FormStatus fromValue(int value) {
        for (FormStatus fs : FormStatus.values()) {
            if (fs.ordinal() == value)
                return fs;
        }
        throw new RuntimeException("Unknown value: " + value);

    }
}
