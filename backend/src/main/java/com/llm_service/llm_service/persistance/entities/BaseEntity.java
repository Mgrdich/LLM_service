package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @CreationTimestamp
    @Column(name = "created_on")
    private Instant createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Instant lastUpdatedOn;
}
