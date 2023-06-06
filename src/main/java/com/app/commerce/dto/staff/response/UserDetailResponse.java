package com.app.commerce.dto.staff.response;

import com.app.commerce.dto.role.response.RoleResponse;
import com.app.commerce.dto.shop.response.ShopResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserDetailResponse extends UserResponse {
    private List<RoleResponse> roles = new ArrayList<>();
    private List<ShopResponse> trackings = new ArrayList<>();
}
