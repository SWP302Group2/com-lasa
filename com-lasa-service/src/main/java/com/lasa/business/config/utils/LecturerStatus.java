package com.lasa.business.config.utils;

public enum LecturerStatus {
    DELETED(-2),
    BANNED(-1),
    NOT_ACTIVATED(0),
    ACTIVATED(1);

    private final int code;

    LecturerStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
