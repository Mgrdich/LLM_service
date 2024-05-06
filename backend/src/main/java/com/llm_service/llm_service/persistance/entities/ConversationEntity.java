package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Data;

@Entity
@Table(name = "conversation")
@Data
public class ConversationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
}
