package com.applyforonce.dto;

import com.applyforonce.entity.Role;
import com.applyforonce.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private UserStatus userStatus;
    private Instant createdAt;

}
