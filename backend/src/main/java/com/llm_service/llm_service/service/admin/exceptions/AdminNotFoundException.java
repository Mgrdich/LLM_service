package com.llm_service.llm_service.service.admin.exceptions;

import java.util.UUID;

public class AdminNotFoundException extends Exception {
    public AdminNotFoundException(UUID id) {
        super("Admin with id " + id.toString() + " is not found");
    }
}
