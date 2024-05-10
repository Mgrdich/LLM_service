package com.llm_service.llm_service.service.admin;

import java.util.UUID;

import com.llm_service.llm_service.service.user.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Admin {
    UUID id;
    User user;
}
