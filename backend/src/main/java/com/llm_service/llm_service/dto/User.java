package com.llm_service.llm_service.dto;

import com.llm_service.llm_service.persistance.entities.Role;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class User {
    UUID id;
    String firstName;
    String lastName;
    String username;
    String password;
    Role role;
}
