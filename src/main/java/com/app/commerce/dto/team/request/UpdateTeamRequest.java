package com.app.commerce.dto.team.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateTeamRequest {

    @JsonDeserialize(using = TrimString.class)
    private String name;

    @JsonDeserialize(using = TrimString.class)
    private String code;

    @JsonDeserialize(using = TrimString.class)
    private String description;
}
