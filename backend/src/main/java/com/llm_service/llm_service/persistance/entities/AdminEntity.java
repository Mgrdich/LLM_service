package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admin")
@Data
public class AdminEntity extends BaseEntity {
    @Enumerated
    @Column(name = "role_in_company")
    private RoleInCompany roleInCompany;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany(mappedBy = "adminUpdated")
    List<FlightEntity> flightsUpdated;

    @ManyToMany(mappedBy = "adminDeleted")
    List<FlightEntity> flightsDeleted;
}
