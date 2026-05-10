package com.carrepair.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegistrationRequest(@NotBlank String username, @Email String email, @NotBlank String password) {
}
