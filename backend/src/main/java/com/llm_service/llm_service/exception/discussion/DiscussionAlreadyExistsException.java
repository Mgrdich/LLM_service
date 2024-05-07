package com.llm_service.llm_service.exception.discussion;

import java.util.UUID;

public class DiscussionAlreadyExistsException extends Exception {
    public DiscussionAlreadyExistsException(UUID id) {
        super("Discussion with id " + id + " already exists");
    }
}
