package com.llm_service.llm_service.exception.conversation;

import java.util.UUID;

public class ConversationAlreadyExistsException extends Exception {
    public ConversationAlreadyExistsException(UUID id) {
        super("Conversation with id " + id + " already exists");
    }
}
