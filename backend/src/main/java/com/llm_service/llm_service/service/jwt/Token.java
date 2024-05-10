package com.llm_service.llm_service.service.jwt;

import com.aua.flightreservationsystem.core.user.User;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class Token {
    UUID id;
    String token;
    boolean loggedOut;
    User user;
}
