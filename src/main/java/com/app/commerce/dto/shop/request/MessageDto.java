package com.app.commerce.dto.shop.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private String content;
    private OffsetDateTime time;
    @JsonProperty("is_admin")
    private Boolean isAdmin;
}
