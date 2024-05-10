package com.llm_service.llm_service.exception.user;

import java.util.UUID;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String username) {
        super("Username with name " + username + " already exists");
    }
}
