package com.app.commerce.dto.staff.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class CreateStaffRequest {
    @NotBlank
    @JsonDeserialize(using = TrimString.class)
    private String username;
    @NotBlank
    @Size(min = 8)
    private String password;
    @JsonDeserialize(using = TrimString.class)
    private String staffId;
    @JsonDeserialize(using = TrimString.class)
    private String fullName;
    @JsonDeserialize(using = TrimString.class)
    private String phoneNumber;
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

    @NotNull
    private Long teamId;
}
