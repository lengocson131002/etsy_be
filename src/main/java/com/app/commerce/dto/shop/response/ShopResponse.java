package com.app.commerce.dto.shop.response;

import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopResponse {
    private String id;
    private String name;
    private String status;
    private String currencySymbol;
    private String currencyCode;
    private OffsetDateTime openedDate;
    private String description;
    private GoLoginProfileResponse profile;
}