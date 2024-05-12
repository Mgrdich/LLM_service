package com.llm_service.llm_service.controller.user;

import com.llm_service.llm_service.dto.User;
import com.llm_service.llm_service.exception.UnAuthorizedException;
import com.llm_service.llm_service.exception.user.UserAlreadyExistsException;
import com.llm_service.llm_service.exception.user.UserNotFoundException;
import com.llm_service.llm_service.exception.user.UsernameAlreadyExistsException;
import com.llm_service.llm_service.service.jwt.AuthenticationResponse;
import com.llm_service.llm_service.service.jwt.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserApiMapper userApiMapper;

    public UserController(AuthenticationService authenticationService, UserApiMapper userApiMapper) {
        this.authenticationService = authenticationService;
        this.userApiMapper = userApiMapper;
    }

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Get Current User Data",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "get the current user")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> register() throws UnAuthorizedException {
        User user = authenticationService.getUser().orElseThrow(UnAuthorizedException::new);
        return ResponseEntity.status(HttpStatus.OK).body(userApiMapper.map(user));
    }

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Creates a new user",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "register the user")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest)
            throws UsernameAlreadyExistsException {
        User user = authenticationService.register(userRequest);
        return ResponseEntity.status(HttpStatus.OK).body(userApiMapper.map(user));
    }

    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Logs the user into the system",
                        content = {@Content(mediaType = "application/json")})
            })
    @Operation(summary = "login phase of the user")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
            throws UserNotFoundException, AuthenticationException {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok(userApiMapper.map(authenticationResponse));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<String> handleUsernameAlreadyExistsExceptions(
            UserAlreadyExistsException usernameAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(usernameAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    ResponseEntity<String> handleUsernameAlreadyExistsExceptions(
            UsernameAlreadyExistsException usernameAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(usernameAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<String> handleUsernameNotFoundExceptions(UserNotFoundException usernameNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usernameNotFoundException.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<String> handleAuthenticationException(AuthenticationException authenticationException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getMessage());
    }
}
