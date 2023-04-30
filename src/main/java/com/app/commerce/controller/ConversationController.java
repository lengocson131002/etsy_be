package com.app.commerce.controller;

import com.app.commerce.config.OpenApiConfig;
import com.app.commerce.dto.common.response.PageResponse;
import com.app.commerce.dto.conversation.request.GetAllConversationsRequest;
import com.app.commerce.dto.conversation.response.ConversationResponse;
import com.app.commerce.entity.Conversation;
import com.app.commerce.entity.User;
import com.app.commerce.enums.ResponseCode;
import com.app.commerce.exception.ApiException;
import com.app.commerce.service.AuthenticationService;
import com.app.commerce.service.ConversationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/conversations")
@RequiredArgsConstructor
@SecurityRequirement(name = OpenApiConfig.BEARER_SCHEME)
public class ConversationController {

    private final ConversationService conversationService;

    private final AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<PageResponse<Conversation, ConversationResponse>> getAllConversations(@Valid @ParameterObject GetAllConversationsRequest request) {
        if (!authenticationService.isAdmin()) {
            User loggedUser = authenticationService.getCurrentAuthenticatedAccount()
                    .orElseThrow(() -> new ApiException(ResponseCode.UNAUTHORIZED));

            if (loggedUser.getTeam() == null) {
                return ResponseEntity.ok(new PageResponse<>());
            }

            request.setTeamId(loggedUser.getTeam().getId());
        }

        PageResponse<Conversation, ConversationResponse> response = conversationService.getAllConversations(request);
        return ResponseEntity.ok(response);
    }
}
