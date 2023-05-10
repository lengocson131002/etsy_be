package com.app.commerce.dto.conversation.response;

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
public class MessageResponse {

    private Long id;
    private String content;
    private OffsetDateTime time;
    private Boolean isAdmin;
    private List<String> images;
}
