package com.llm_service.llm_service.persistance.repositories.customer;

import java.util.UUID;

import com.llm_service.llm_service.persistance.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {}
