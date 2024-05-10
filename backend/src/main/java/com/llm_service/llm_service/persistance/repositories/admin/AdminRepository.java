package com.llm_service.llm_service.persistance.repositories.admin;

import com.aua.flightreservationsystem.persistence.model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {}
