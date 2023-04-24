package com.app.commerce.dto.shop.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateShopRequest {
    @JsonProperty("name")
    @JsonDeserialize(using = TrimString.class)
    private String name;

    @JsonProperty("status")
    @JsonDeserialize(using = TrimString.class)
    private String status;

    @JsonProperty("currency_symbol")
    @JsonDeserialize(using = TrimString.class)
    private String currencySymbol;

    @JsonProperty("currency_code")
    @JsonDeserialize(using = TrimString.class)
    private String currencyCode;
    @JsonProperty("opened_date")
    private OffsetDateTime openedDate;

    @JsonProperty("description")
    @JsonDeserialize(using = TrimString.class)
    private String description;

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
