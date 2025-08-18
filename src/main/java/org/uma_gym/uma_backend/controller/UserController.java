package org.uma_gym.uma_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uma_gym.uma_backend.dto.UserRegistrationDto;
import org.uma_gym.uma_backend.model.User;
import org.uma_gym.uma_backend.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Register a new user", description = "Creates a new user account in the system. Can only be performed by an administrator.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "409", description = "Email already exists",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserRegistrationDto registrationDto) {
        try {
            User registeredUser = userService.registerUser(registrationDto);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}