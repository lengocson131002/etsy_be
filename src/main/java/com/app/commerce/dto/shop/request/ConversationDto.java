package com.app.commerce.dto.shop.request;

import com.app.commerce.annotations.TrimString;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDto {
    @JsonProperty("customer_name")
    @JsonDeserialize(using = TrimString.class)
    private String customerName;
    @JsonProperty("unread_count")
    @PositiveOrZero
    private Integer unreadCount;
    @JsonProperty("message_time")
    @JsonDeserialize(using = TrimString.class)
    private String messageTime;
}
