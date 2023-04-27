package com.app.commerce.dto.conversation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ConversationResponse {
    public Long id;

    private String customerName;

    private Integer unreadCount;

    private String messageTime;

    private String shopId;

    private String shopName;
}
