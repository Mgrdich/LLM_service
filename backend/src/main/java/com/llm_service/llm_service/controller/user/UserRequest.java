package com.llm_service.llm_service.controller.user;

import com.llm_service.llm_service.constraint.ValidPassword;
import com.llm_service.llm_service.constraint.ValidUsername;
import com.llm_service.llm_service.persistance.entities.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserRequest {
    @ValidUsername
    String username;

    @ValidPassword
    String password;

    @NotBlank(message = "firstname is required")
    String firstName;

    @NotBlank(message = "lastname is required")
    String lastName;

    @NonNull
    Role role;
}
