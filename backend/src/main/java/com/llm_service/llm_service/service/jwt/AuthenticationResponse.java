package com.llm_service.llm_service.service.jwt;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
    private String message;

    public AuthenticationResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
