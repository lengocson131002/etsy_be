package com.app.commerce.dto.shop.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDto {
    @JsonProperty("customer_name")
    @JsonDeserialize(using = TrimString.class)
    private String customerName;

    @JsonProperty("customer_image")
    @JsonDeserialize(using = TrimString.class)
    private String customerImage;

    @JsonProperty("unread_count")
    @PositiveOrZero
    private Integer unreadCount;
    @JsonProperty("message_time")
    @JsonDeserialize(using = TrimString.class)
    private String messageTime;

    @JsonProperty("messages")
    private List<MessageDto> messages;
}
