package com.app.commerce.dto.team.response;

import com.app.commerce.dto.shop.response.ShopResponse;
import com.app.commerce.dto.staff.response.UserResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamDetailResponse extends TeamResponse {
    List<ShopResponse> shops;
    List<UserResponse> staffs;
}
