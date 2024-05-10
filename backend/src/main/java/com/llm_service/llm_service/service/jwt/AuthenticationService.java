package com.llm_service.llm_service.service.jwt;

import com.llm_service.llm_service.controller.user.LoginRequest;
import com.llm_service.llm_service.controller.user.UserRequest;
import com.llm_service.llm_service.dto.User;
import com.llm_service.llm_service.exception.user.UserNotFoundException;
import com.llm_service.llm_service.exception.user.UsernameAlreadyExistsException;
import com.llm_service.llm_service.persistance.entities.UserEntity;
import com.llm_service.llm_service.persistance.repositories.token.TokenPersistenceManager;
import com.llm_service.llm_service.persistance.repositories.user.UserEntityMapper;
import com.llm_service.llm_service.persistance.repositories.user.UserPersistenceManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserPersistenceManager userPersistenceManager;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenPersistenceManager tokenPersistenceManager;

    private final AuthenticationManager authenticationManager;

    private final UserEntityMapper userEntityMapper;

    public User register(UserRequest userRequest) throws UsernameAlreadyExistsException {
        User user = User.builder()
                .role(userRequest.getRole())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .build();

        // check if user already exist. if exist than authenticate the user
        if (userPersistenceManager.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(user.getUsername());
        }

        User newUser = user.toBuilder()
                .password(this.passwordEncoder.encode(user.getPassword()))
                .build();

        UserEntity userEntity = userEntityMapper.map(newUser);

        User savedUser = userPersistenceManager.save(userEntityMapper.map(userEntity));

        String jwt = jwtService.generateToken(savedUser);

        saveUserToken(jwt, savedUser);

        return savedUser;
    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest)
            throws UserNotFoundException, AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        User user = userPersistenceManager
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(UserNotFoundException::new);
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User login was successful");
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenPersistenceManager.findAllTokensByUser(user.getId());
        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> t.toBuilder().loggedOut(false).build());

        tokenPersistenceManager.saveAll(validTokens);
    }

    private void saveUserToken(String jwt, User user) {
        Token token = Token.builder().token(jwt).loggedOut(false).user(user).build();

        tokenPersistenceManager.save(token);
    }
}
