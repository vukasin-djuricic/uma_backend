package org.uma_gym.uma_backend.dto;

import lombok.Data;
import org.uma_gym.uma_backend.model.enums.Role;
import java.time.LocalDate;

@Data
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password; //heširati na backendu
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Role role;
}