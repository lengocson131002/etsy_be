package com.app.commerce.service;

import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.conversation.request.GetAllConversationsRequest;
import com.app.commerce.dto.conversation.response.ConversationResponse;
import com.app.commerce.entity.Conversation;

public interface ConversationService {
    PageResponse<Conversation, ConversationResponse> getAllConversations(GetAllConversationsRequest request);

}
