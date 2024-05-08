package com.llm_service.llm_service.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder(toBuilder = true)
@Data
public abstract class Base {
    UUID id;
    Instant createdOn;
    Instant lastUpdatedOn;
}
