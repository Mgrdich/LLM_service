package com.llm_service.llm_service.exception.user;

import java.util.UUID;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(UUID id) {
        super("User with id " + id + " is not found");
    }

    public UserNotFoundException() {
        super("User with is not found");
    }
}
