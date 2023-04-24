package com.app.commerce.dto.shop.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ShopDto {
    @JsonProperty("shop")
    @Valid
    @NotNull
    private ShopInfoDto shop;

    @JsonProperty("profile")
    @Valid
    @NotNull
    private GoLoginProfileDto profile;

    @JsonProperty("dashboard")
    @Valid
    private DashboardDto dashboard;

    @JsonProperty("listings")
    @Valid
    private List<ListingDto> listings;

    @JsonProperty("conversations")
    @Valid
    private List<ConversationDto> conversations;

    @JsonProperty("orders")
    @Valid
    private List<OrderDto> orders;
}
