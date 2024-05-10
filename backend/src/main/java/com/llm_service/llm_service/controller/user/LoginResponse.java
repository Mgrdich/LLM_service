package com.llm_service.llm_service.controller.user;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class LoginResponse {
    String token;
}
