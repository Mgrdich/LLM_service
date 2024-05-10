package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
@Data
public class CustomerEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "customer")
    private List<FlightEntity> flights;

    @OneToMany(mappedBy = "customer")
    private List<PassportInfoEntity> passportInfos;
}
