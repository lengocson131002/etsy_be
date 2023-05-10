package com.app.commerce.dto.shop.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private String content;
    private OffsetDateTime time;
    private Boolean isAdmin;
    private List<String> images;
}
