package com.llm_service.llm_service.controller.user;

import lombok.Value;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@SuperBuilder
public class UserResponse {
    String name;
}
