package com.llm_service.llm_service.service.user;

import com.llm_service.llm_service.dto.User;
import com.llm_service.llm_service.persistance.repositories.user.UserPersistenceManager;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserContext {
    private final UserPersistenceManager userPersistenceManager;

    public Optional<User> getUserFromContext() {
        // Get the authentication object from SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Access user information from UserDetails
            String username = userDetails.getUsername();
            // You can access other user details as well, like authorities: userDetails.getAuthorities()

            return userPersistenceManager.findByUsername(username);
        }

        return Optional.empty();
    }
}
