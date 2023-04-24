package com.app.commerce.dto.staff.response;

import com.app.commerce.dto.role.response.RoleResponse;
import com.app.commerce.dto.shop.response.ShopResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class UserResponse {
    private Long id;
    private String staffId;
    private String username;
    private String phoneNumber;
    private String email;
    private String fullName;
    private String address;
    private String description;
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime updatedAt;
    private String updatedBy;
    private List<RoleResponse> roles;
    private List<ShopResponse> trackings;
}
