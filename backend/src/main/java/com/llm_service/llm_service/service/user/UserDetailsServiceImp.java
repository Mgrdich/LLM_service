package com.llm_service.llm_service.service.user;

import com.aua.flightreservationsystem.persistence.repository.user.UserEntityMapper;
import com.aua.flightreservationsystem.persistence.repository.user.UserPersistenceManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private final UserPersistenceManager userPersistenceManager;
    private final UserEntityMapper userEntityMapper;

    public UserDetailsServiceImp(UserPersistenceManager userPersistenceManager, UserEntityMapper userEntityMapper) {
        this.userPersistenceManager = userPersistenceManager;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userPersistenceManager
                .findByUsername(username)
                .map(userEntityMapper::map)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
