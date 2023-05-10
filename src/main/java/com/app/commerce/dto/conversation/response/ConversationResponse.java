package com.app.commerce.dto.conversation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ConversationResponse {
    public Long id;

    private String customerName;
    private String customerImage;
    private Integer unreadCount;

    private String messageTime;

    private OffsetDateTime lastMessageTime;
    private String shopId;

    private String shopName;
}
