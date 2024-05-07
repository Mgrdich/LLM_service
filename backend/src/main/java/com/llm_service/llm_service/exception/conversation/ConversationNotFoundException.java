package com.llm_service.llm_service.exception.conversation;

import java.util.UUID;

public class ConversationNotFoundException extends Exception {
    public ConversationNotFoundException(UUID id) {
        super("Conversation with id " + id + " is not found");
    }
}
