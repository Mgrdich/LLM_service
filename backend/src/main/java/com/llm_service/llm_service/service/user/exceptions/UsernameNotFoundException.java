package com.llm_service.llm_service.service.user.exceptions;

public class UsernameNotFoundException extends Exception {
    public UsernameNotFoundException(String username) {
        super("Username " + username + " is not found");
    }
}
