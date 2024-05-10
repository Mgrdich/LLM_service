package com.llm_service.llm_service.persistance.repositories.employee;

import com.aua.flightreservationsystem.persistence.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {}
