package com.app.commerce.mappings;

import com.app.commerce.dto.conversation.response.ConversationDetailResponse;
import com.app.commerce.dto.conversation.response.ConversationResponse;
import com.app.commerce.dto.conversation.response.MessageResponse;
import com.app.commerce.dto.shop.request.ConversationDto;
import com.app.commerce.dto.shop.request.MessageDto;
import com.app.commerce.entity.Conversation;
import com.app.commerce.entity.Message;

public interface ConversationMapper {
    Conversation toEntity(ConversationDto dto);

    ConversationResponse toResponse(Conversation conversation);

    ConversationDetailResponse toDetailResponse(Conversation conversation);

    MessageResponse toMessageResponse(Message message);

    Message toMessageEntity(MessageDto messageDto);
}
