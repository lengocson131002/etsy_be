package com.app.commerce.mappings;

import com.app.commerce.dto.staff.request.CreateStaffRequest;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.entity.User;

public interface UserMapper {
    User toEntity(CreateStaffRequest request);

    UserResponse toResponse(User user);
}
