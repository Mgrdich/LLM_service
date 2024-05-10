package com.llm_service.llm_service.dto;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
@Data
public class Conversation extends Base {
    String title;
    List<Discussion> discussions;
}
