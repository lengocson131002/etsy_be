package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.staff.request.CreateStaffRequest;
import com.app.commerce.dto.staff.request.GetAllStaffRequest;
import com.app.commerce.dto.staff.request.UpdateStaffRequest;
import com.app.commerce.dto.staff.response.UserResponse;
import com.app.commerce.entity.User;

import java.util.List;

public interface StaffService {
    UserResponse createStaff(CreateStaffRequest request);

    PageResponse<User, UserResponse> getAllStaffs(GetAllStaffRequest request);

    UserResponse getStaff(Long id);

    void updateStaff(Long id, UpdateStaffRequest request);

    void removeStaff(Long id);

    void addTracking(User staff, String shopId);

    void unTracking(User staff, String shopId);

}
