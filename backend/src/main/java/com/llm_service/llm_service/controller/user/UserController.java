package com.llm_service.llm_service.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserApiMapper userApiMapper;

    public UserController(AuthenticationService authenticationService, UserApiMapper userApiMapper) {
        this.authenticationService = authenticationService;
        this.userApiMapper = userApiMapper;
    }

    // TODO this should be hidden some other way for it to be completely secure
    //  but for this current purpose it is fine
    //  or done by migration or the project setup
    @PostMapping("/register_admin")
    public ResponseEntity<UserResponse> registerAdmin(@RequestBody AdminRequest adminRequest)
            throws UsernameAlreadyExistsException {
        Admin admin = authenticationService.registerAdmin(adminRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userApiMapper.map(admin));
    }

    @PostMapping("/admin_only/register_employee")
    public ResponseEntity<UserResponse> registerEmployee(@RequestBody EmployeeRequest employeeRequest)
            throws UsernameAlreadyExistsException {
        Employee employee = authenticationService.registerEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userApiMapper.map(employee));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody CustomerRequest customerRequest)
            throws UsernameAlreadyExistsException {
        Customer customer = authenticationService.registerCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userApiMapper.map(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
            throws UsernameNotFoundException, AuthenticationException {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok(userApiMapper.map(authenticationResponse));
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    ResponseEntity<String> handleUsernameAlreadyExistsExceptions(
            UsernameAlreadyExistsException usernameAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(usernameAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    ResponseEntity<String> handleUsernameNotFoundExceptions(UsernameNotFoundException usernameNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usernameNotFoundException.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<String> handleAuthenticationException(AuthenticationException authenticationException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getMessage());
    }
}
