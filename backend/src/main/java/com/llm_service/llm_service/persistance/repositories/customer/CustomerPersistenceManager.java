package com.llm_service.llm_service.persistance.repositories.customer;

import com.aua.flightreservationsystem.core.customer.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerPersistenceManager {
    List<Customer> findAll();

    Optional<Customer> findById(UUID id);

    Customer save(Customer customer);

    void delete(UUID id);
}
