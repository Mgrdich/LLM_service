package com.llm_service.llm_service.model;

import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public class Conversation {
    UUID id;
}
