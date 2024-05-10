package com.llm_service.llm_service.service.jwt;

import com.aua.flightreservationsystem.api.user.*;
import com.aua.flightreservationsystem.core.admin.Admin;
import com.aua.flightreservationsystem.core.customer.Customer;
import com.aua.flightreservationsystem.core.employee.Employee;
import com.aua.flightreservationsystem.core.user.User;
import com.aua.flightreservationsystem.core.user.exceptions.UsernameAlreadyExistsException;
import com.aua.flightreservationsystem.core.user.exceptions.UsernameNotFoundException;
import com.aua.flightreservationsystem.persistence.model.Role;
import com.aua.flightreservationsystem.persistence.model.UserEntity;
import com.aua.flightreservationsystem.persistence.repository.admin.AdminPersistenceManager;
import com.aua.flightreservationsystem.persistence.repository.customer.CustomerPersistenceManager;
import com.aua.flightreservationsystem.persistence.repository.employee.EmployeePersistenceManager;
import com.aua.flightreservationsystem.persistence.repository.token.TokenPersistenceManager;
import com.aua.flightreservationsystem.persistence.repository.user.UserEntityMapper;
import com.aua.flightreservationsystem.persistence.repository.user.UserPersistenceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserPersistenceManager userPersistenceManager;
    private final AdminPersistenceManager adminPersistenceManager;
    private final CustomerPersistenceManager customerPersistenceManager;
    private final EmployeePersistenceManager employeePersistenceManager;

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
                .roleInCompany(adminRequest.getRoleInCompany())
                .build();
        return adminPersistenceManager.save(admin);
    }

    public Customer registerCustomer(CustomerRequest customerRequest) throws UsernameAlreadyExistsException {
        User user = register(customerRequest, Role.CUSTOMER);
        Customer customer = Customer.builder().user(user).build();
        return customerPersistenceManager.save(customer);
    }

    public Employee registerEmployee(EmployeeRequest employeeRequest) throws UsernameAlreadyExistsException {
        User user = register(employeeRequest, Role.EMPLOYEE);
        Employee employee = Employee.builder()
                .user(user)
                .contact(employeeRequest.getContact())
                .salary(employeeRequest.getSalary())
                .build();
        return employeePersistenceManager.save(employee);
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
