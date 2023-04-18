package com.app.commerce.dto.shop.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoLoginProfileDto {
    @JsonDeserialize(using = TrimString.class)
    @JsonProperty("name")
    private String name;
    @JsonProperty("notes")
    @JsonDeserialize(using = TrimString.class)
    private String notes;
    @JsonProperty("created_date")
    private OffsetDateTime createdDate;
    @JsonDeserialize(using = TrimString.class)
    @JsonProperty("proxy")
    private String proxy;
    @JsonDeserialize(using = TrimString.class)
    @JsonProperty("folder_name")
    private String folderName;
}
