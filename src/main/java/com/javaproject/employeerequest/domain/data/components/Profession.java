package com.javaproject.employeerequest.domain.data.components;

public enum Profession {
    UNSELECTED, NANNY, DRIVER;

    public static Profession fromValue(int value) {
        for (Profession p : Profession.values()) {
            if (p.ordinal() == value)
                return p;
        }
        throw new RuntimeException("Unknown value: " + value);
    }

}
