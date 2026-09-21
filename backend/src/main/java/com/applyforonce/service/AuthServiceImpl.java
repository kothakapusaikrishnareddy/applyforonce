package com.applyforonce.service;

import com.applyforonce.dto.RegisterRequest;
import com.applyforonce.dto.RegisterResponse;
import com.applyforonce.entity.Role;
import com.applyforonce.entity.User;
import com.applyforonce.entity.UserStatus;
import com.applyforonce.exception.EmailAlreadyExistsException;
import com.applyforonce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger log =
            LoggerFactory.getLogger(AuthService.class);


    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }



        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.CANDIDATE);
        user.setUserStatus(UserStatus.ACTIVE);
        Instant now = Instant.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);


        User savedUser = userRepository.save(user);

        log.info("User with email {} has been saved", user.getEmail());



        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getEmail(),
                savedUser.getRole(),
                savedUser.getUserStatus(),
                savedUser.getCreatedAt()
        );
    }
}
