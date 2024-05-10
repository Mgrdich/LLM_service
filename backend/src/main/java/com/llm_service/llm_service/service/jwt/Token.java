package com.llm_service.llm_service.service.jwt;

import java.util.UUID;

import com.llm_service.llm_service.dto.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Token {
    UUID id;
    String token;
    boolean loggedOut;
    User user;
}
