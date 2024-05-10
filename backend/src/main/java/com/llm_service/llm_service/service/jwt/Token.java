package com.llm_service.llm_service.service.jwt;

import com.llm_service.llm_service.dto.User;
import java.util.UUID;
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
