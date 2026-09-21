package com.applyforonce.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Please enter the first name")
    private String firstName;

    @NotBlank(message = "Please enter the last name")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter valid email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*!]).+$",
            message = "Password must contain uppercase, lowercase, digit and special character"
    )
    private String password;
}
