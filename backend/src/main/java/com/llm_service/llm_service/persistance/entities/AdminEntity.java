package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admin")
@Data
public class AdminEntity extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
