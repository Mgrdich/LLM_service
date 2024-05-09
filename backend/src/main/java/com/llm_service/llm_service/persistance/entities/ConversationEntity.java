package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "conversation")
@Data
public class ConversationEntity extends BaseEntity {
    @OneToMany
    private List<DiscussionEntity> discussions;
}
