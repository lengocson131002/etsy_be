package com.app.commerce.dto.shop.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ShopInfoDto {
    @JsonProperty("id")
    @NotNull
    private String id;

    @JsonProperty("name")
    @JsonDeserialize(using = TrimString.class)
    @NotNull
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
}
