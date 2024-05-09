package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "discussion")
@Data
public class DiscussionEntity extends BaseEntity {

    @Column(name = "text")
    private String text;

    @Column(name = "role")
    private DiscussionRole promptRole;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private ConversationEntity conversation;
}
