package com.app.commerce.dto.team.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class TeamResponse {
    private Long id;
    private String name;
    private String code;
    private String description;
    private OffsetDateTime createdAt;
    private String createdBy;
    private OffsetDateTime updatedAt;
    private String updatedBy;
    private Integer shopCount;
    private Integer staffCount;
}
