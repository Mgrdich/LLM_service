package com.llm_service.llm_service.controller.user;

import com.llm_service.llm_service.persistance.entities.Role;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserRequest {
    @NonNull
    String username;

    @NonNull
    String password;

    @NonNull
    String firstName;

    @NonNull
    String lastName;

    @NonNull
    Role role;
}
