package com.lasa.business.config.utils;

public enum StudentStatus {
    DELETED(-2),
    BANNED(-1),
    NOT_ACTIVATED(0),
    ACTIVATED(1);

    private final int code;

    StudentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
