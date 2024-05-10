package com.llm_service.llm_service.service.jwt;

import java.util.List;

import com.llm_service.llm_service.controller.user.AdminRequest;
import com.llm_service.llm_service.controller.user.CustomerRequest;
import com.llm_service.llm_service.controller.user.LoginRequest;
import com.llm_service.llm_service.controller.user.UserRequest;
import com.llm_service.llm_service.persistance.entities.Role;
import com.llm_service.llm_service.persistance.entities.UserEntity;
import com.llm_service.llm_service.persistance.repositories.admin.AdminPersistenceManager;
import com.llm_service.llm_service.persistance.repositories.customer.CustomerPersistenceManager;
import com.llm_service.llm_service.persistance.repositories.token.TokenPersistenceManager;
import com.llm_service.llm_service.persistance.repositories.user.UserEntityMapper;
import com.llm_service.llm_service.persistance.repositories.user.UserPersistenceManager;
import com.llm_service.llm_service.service.admin.Admin;
import com.llm_service.llm_service.service.customer.Customer;
import com.llm_service.llm_service.service.user.User;
import com.llm_service.llm_service.service.user.exceptions.UsernameAlreadyExistsException;
import com.llm_service.llm_service.service.user.exceptions.UsernameNotFoundException;
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
    private final AdminPersistenceManager adminPersistenceManager;
    private final CustomerPersistenceManager customerPersistenceManager;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final TokenPersistenceManager tokenPersistenceManager;

    private final AuthenticationManager authenticationManager;

    private final UserEntityMapper userEntityMapper;

    private User register(UserRequest userRequest, Role role) throws UsernameAlreadyExistsException {
        User user = User.builder()
                .role(role)
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

    public Admin registerAdmin(AdminRequest adminRequest) throws UsernameAlreadyExistsException {
        User user = register(adminRequest, Role.ADMIN);
        Admin admin = Admin.builder()
                .user(user)
                .build();
        return adminPersistenceManager.save(admin);
    }

    public Customer registerCustomer(CustomerRequest customerRequest) throws UsernameAlreadyExistsException {
        User user = register(customerRequest, Role.CUSTOMER);
        Customer customer = Customer.builder().user(user).build();
        return customerPersistenceManager.save(customer);
    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest)
            throws UsernameNotFoundException, AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        User user = userPersistenceManager
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(loginRequest.getUsername()));
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
