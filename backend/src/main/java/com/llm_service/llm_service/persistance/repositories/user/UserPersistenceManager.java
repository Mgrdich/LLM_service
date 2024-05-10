package com.llm_service.llm_service.persistance.repositories.user;

import com.aua.flightreservationsystem.core.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserPersistenceManager {
    List<User> findAll();

    Optional<User> findById(UUID id);

    Optional<User> findByUsername(String username);

    User save(User customer);

    void delete(UUID id); // TODO to be removed in the future
}
