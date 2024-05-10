package com.llm_service.llm_service.controller.user;

import com.llm_service.llm_service.service.jwt.AuthenticationResponse;
import com.llm_service.llm_service.service.jwt.AuthenticationService;
import com.llm_service.llm_service.service.user.User;
import com.llm_service.llm_service.service.user.exceptions.UsernameAlreadyExistsException;
import com.llm_service.llm_service.service.user.exceptions.UsernameNotFoundException;
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

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest)
            throws UsernameAlreadyExistsException {
        User user= authenticationService.register(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userApiMapper.map(user));
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
