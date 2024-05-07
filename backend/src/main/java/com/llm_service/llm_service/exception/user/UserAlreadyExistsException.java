package com.llm_service.llm_service.exception.user;

import java.util.UUID;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(UUID id) {
        super("User with id " + id + " already exists");
    }
}
