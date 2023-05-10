package com.app.commerce.dto.shop.response;

import com.app.commerce.dto.profile.response.GoLoginProfileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

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
    private Integer orderCount;
    private Integer visitCount;
    private Double conversionRate;
    private BigDecimal revenue;
    private Boolean isTracked;
    private Long teamId;
    private String teamName;
    private List<String> trackers;
    private OffsetDateTime lastSyncAt;
    private String avatar;
    private String banner;
}
