package com.app.commerce.dto.role.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RoleResponse {
    private Long id;
    private String name;
    private String code;
    private String description;
}
