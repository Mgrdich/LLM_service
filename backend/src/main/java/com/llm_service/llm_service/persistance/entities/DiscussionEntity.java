package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(
        callSuper = true,
        exclude = {"conversation"})
@Entity
@Table(name = "discussion")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionEntity extends BaseEntity {

    @Column(name = "text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "role")
    private DiscussionRole promptRole;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private ConversationEntity conversation;
}
