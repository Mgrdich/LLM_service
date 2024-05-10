package com.llm_service.llm_service.persistance.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee")
@Data
public class EmployeeEntity extends BaseEntity {
    @Column(name = "salary", columnDefinition = "Decimal(10,2) default '0.0'")
    BigDecimal salary;

    @Column(name = "contact")
    String contact;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany(mappedBy = "employees")
    List<FlightEntity> flights;
}
