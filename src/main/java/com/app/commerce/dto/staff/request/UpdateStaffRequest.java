package com.app.commerce.dto.staff.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class UpdateStaffRequest {
    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    private String username;

    @Size(min = 8)
    private String password;
    @JsonDeserialize(using = TrimString.class)
    private String staffId;
    @JsonDeserialize(using = TrimString.class)
    private String fullName;

    @JsonDeserialize(using = TrimString.class)
    private String phoneNumber;

    @Email
    @JsonDeserialize(using = TrimString.class)
    private String email;
    @JsonDeserialize(using = TrimString.class)
    private String address;
    @JsonDeserialize(using = TrimString.class)
    private String description;
    @NotNull
    @UniqueElements
    @NotEmpty
    private List<String> roles;

    private List<Long> teamIds;
}
