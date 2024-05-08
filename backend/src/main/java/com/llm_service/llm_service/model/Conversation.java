package com.llm_service.llm_service.model;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
@Builder(toBuilder = true)
public class Conversation extends Base {
    List<UUID> discussions;
}
