package com.lasa.business.config.utils;

public enum SlotStatus {
    DELETED(-1),
    CANCELED(0),
    CREATED(1),
    ACCEPTED(2),
    NOTIFIED(3),
    COMPLETED(4);

    private final int code;

    SlotStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
