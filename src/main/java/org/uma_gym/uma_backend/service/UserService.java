package org.uma_gym.uma_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.uma_gym.uma_backend.dto.UserRegistrationDto;
import org.uma_gym.uma_backend.model.User;
import org.uma_gym.uma_backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserRegistrationDto registrationDto) {
        //if (email in DB)
        userRepository.findByEmail(registrationDto.getEmail()).ifPresent(user -> {
            throw new IllegalStateException("Email " + user.getEmail() + " is already taken.");
        });

        User newUser = new User();
        newUser.setFirstName(registrationDto.getFirstName());
        newUser.setLastName(registrationDto.getLastName());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setDateOfBirth(registrationDto.getDateOfBirth());
        newUser.setPhoneNumber(registrationDto.getPhoneNumber());
        newUser.setRole(registrationDto.getRole());

        newUser.setPasswordHash(passwordEncoder.encode(registrationDto.getPassword()));

        //INSERT INTO users (...
        return userRepository.save(newUser);
    }
}