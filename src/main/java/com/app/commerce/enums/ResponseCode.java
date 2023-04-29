package com.app.commerce.enums;

public enum ResponseCode {

    INVALID_USERNAME_OR_PASSWORD(102, "Invalid username or password"),

    //Shop
    SHOP_ERROR_NOT_FOUND(202, "Shop not found"),
    SHOP_ERROR_INVALID_INFO(203, "Invalid shop information. Shop information is required"),


    //Role
    ROLE_ERROR_NOT_FOUND(302, "Role not found"),

    //STAFF
    STAFF_ERROR_USERNAME_EXISTED(402, "Username existed"),

    STAFF_ERROR_NOT_FOUND(403, "Account not found"),

    STAFF_ERROR_STAFF_ID_EXISTED(402, "Staff ID existed"),


    //LISTING
    LISTING_ERROR_NOT_FOUND(501, "Listing not found"),

    //ORDER
    ORDER_ERROR_NOT_FOUND(601, "Order not found"),

    //PROFILE
    PROFILE_ERROR_EXISTED(701, "Profile existed"),

    PROFILE_ERROR_INVALID_CREATED_DATE(702, "Invalid profile created date"),

    PROFILE_ERROR_NOT_FOUND(703, "Profile not found"),

    PROFILE_ERROR_REFERENCED(704, "Profile is currently reference by a shop"),

    //TEAM
    TEAM_ERROR_EXISTS_NAME(801, "Team name existed"),

    TEAM_ERROR_EXISTS_CODE(802, "Team code existed"),

    TEAM_ERROR_NOT_FOUND(803, "Team not found"),

    TEAM_ERROR_STAFF_ASSIGNED(804, "staff was assigned"),

    TEAM_ERROR_SHOP_ASSIGNED(805, "Shop was assigned"),


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
