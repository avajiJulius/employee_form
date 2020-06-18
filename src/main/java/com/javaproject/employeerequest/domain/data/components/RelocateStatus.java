package com.javaproject.employeerequest.domain.data.components;

public enum RelocateStatus {
    UNSELECTED, IMPOSSIBLE, PERHAPS, DESIRABLE, UNDESIRABLE;

    public static RelocateStatus fromValue(int value) {
        for (RelocateStatus rs : RelocateStatus.values()) {
            if (rs.ordinal() == value)
                return rs;
        }
        throw new RuntimeException("Unknown values: " + value);
    }
}
