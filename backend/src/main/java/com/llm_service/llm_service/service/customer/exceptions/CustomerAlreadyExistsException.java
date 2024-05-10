package com.llm_service.llm_service.service.customer.exceptions;

import java.util.UUID;

public class CustomerAlreadyExistsException extends Exception {
    public CustomerAlreadyExistsException(UUID id) {
        super("The customer with id " + id.toString() + " already exists");
    }
}
