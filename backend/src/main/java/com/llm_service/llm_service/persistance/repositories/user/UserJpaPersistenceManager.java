package com.llm_service.llm_service.persistance.repositories.user;

import com.aua.flightreservationsystem.core.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserJpaPersistenceManager implements UserPersistenceManager {
    private final UserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    @Autowired
    public UserJpaPersistenceManager(UserRepository userRepository, UserEntityMapper customerEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = customerEntityMapper;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().map(userEntityMapper::map).toList();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id).map(userEntityMapper::map);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username).map(userEntityMapper::map);
    }

    @Override
    public User save(User customer) {
        return userEntityMapper.map(userRepository.save(userEntityMapper.map(customer)));
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
