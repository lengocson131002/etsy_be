package com.app.commerce.dto.team.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTeamRequest {
    @NotNull
    @JsonDeserialize(using = TrimString.class)
    private String name;

    @JsonDeserialize(using = TrimString.class)
    private String code;

    @JsonDeserialize(using = TrimString.class)
    private String description;
}
