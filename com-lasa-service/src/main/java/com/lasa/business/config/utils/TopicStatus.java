package com.lasa.business.config.utils;

public enum TopicStatus {
    DELETED(-1),
    NOT_ACTIVATED(0),
    ACTIVATED(1);

    private final int code;

    TopicStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
