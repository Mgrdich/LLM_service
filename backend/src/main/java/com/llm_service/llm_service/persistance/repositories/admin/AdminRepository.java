package com.llm_service.llm_service.persistance.repositories.admin;

import java.util.UUID;

import com.llm_service.llm_service.persistance.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {}
