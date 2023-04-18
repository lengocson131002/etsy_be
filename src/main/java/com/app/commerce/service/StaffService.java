package com.app.commerce.service;

import com.app.commerce.dto.staff.request.CreateStaffRequest;
import com.app.commerce.dto.staff.response.UserResponse;

public interface StaffService {
    UserResponse createStaff(CreateStaffRequest request);
}
