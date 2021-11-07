package com.lasa.business.config.utils;

public enum BookingRequestStatus {
    DELETED(-2),
    DENIED(-1),
    CANCELED(0),
    CREATED(1),
    ACCEPTED(2),
    NOTIFIED(3),
    COMPLETED(4);
    private final int code;

    BookingRequestStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
