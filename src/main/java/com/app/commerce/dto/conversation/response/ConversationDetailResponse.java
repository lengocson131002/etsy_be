package com.app.commerce.dto.conversation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationDetailResponse extends ConversationResponse{
    private List<MessageResponse> messages;
}
