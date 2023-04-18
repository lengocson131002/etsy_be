package com.app.commerce.enums;

public enum ResponseCode {

    INVALID_USERNAME_OR_PASSWORD(102, "Invalid username or password"),

    //Shop
    SHOP_ERROR_NOT_FOUND(202, "Shop not found"),
    SHOP_ERROR_INVALID_INFO(203, "Invalid shop information"),


    //Role
    ROLE_ERROR_NOT_FOUND(302, "Role not found"),

    //USER
    USER_ERROR_EXISTED(402, "Username existed"),

    //LISTING
    LISTING_ERROR_NOT_FOUND(501, "Listing not found"),

    //ORDER
    ORDER_ERROR_NOT_FOUND(601, "Order not found")
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
