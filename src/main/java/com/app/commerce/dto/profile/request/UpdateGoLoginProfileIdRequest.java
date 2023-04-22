package com.app.commerce.dto.profile.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateGoLoginProfileIdRequest {
    @NotNull
    @JsonDeserialize(using = TrimString.class)
    private String goLoginProfileId;
    @NotNull
    @JsonDeserialize(using = TrimString.class)
    private String name;
    @NotNull
    @JsonDeserialize(using = TrimString.class)
    private String proxy;
    @JsonDeserialize(using = TrimString.class)
    private String folderName;
    @JsonDeserialize(using = TrimString.class)
    private String notes;
    @NotNull
    private OffsetDateTime createdDate;
}
