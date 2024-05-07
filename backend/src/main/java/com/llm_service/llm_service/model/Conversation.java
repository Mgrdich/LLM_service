package com.llm_service.llm_service.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Conversation {
    UUID id;
    String text;
}
