package com.llm_service.llm_service.service.user.exceptions;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException(String username) {
        super("The username " + username + " already exists");
    }
}
