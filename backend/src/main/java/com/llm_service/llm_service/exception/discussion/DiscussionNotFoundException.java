package com.llm_service.llm_service.exception.discussion;

import java.util.UUID;

public class DiscussionNotFoundException extends Exception {
    public DiscussionNotFoundException(UUID id) {
        super("Discussion with id " + id + " is not found");
    }
}
