package com.llm_service.llm_service.persistance.repositories.admin;

import com.llm_service.llm_service.service.admin.Admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdminPersistenceManager {
    List<Admin> findAll();

    Optional<Admin> findById(UUID id);

    Admin save(Admin admin);

    void delete(UUID id);
}
