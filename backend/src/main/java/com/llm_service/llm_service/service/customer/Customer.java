package com.llm_service.llm_service.service.customer;

import java.util.UUID;

import com.llm_service.llm_service.service.user.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Customer {
    UUID id;
    User user;
}
