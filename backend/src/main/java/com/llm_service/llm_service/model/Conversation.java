package com.llm_service.llm_service.model;

import java.util.UUID;
import lombok.Builder;

@Builder(toBuilder = true)
public class Conversation {
    UUID id;
}
