package com.app.commerce.enums;

public enum ResponseCode {

    INVALID_USERNAME_OR_PASSWORD(402, "Invalid username or password")
    ;

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
