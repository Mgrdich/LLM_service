package com.llm_service.llm_service.controller.user;

import com.llm_service.llm_service.persistance.entities.Role;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public abstract class UserRequest {
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected Role role;
}
