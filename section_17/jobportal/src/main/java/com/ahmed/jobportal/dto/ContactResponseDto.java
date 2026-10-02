package com.ahmed.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.Instant;

public record ContactResponseDto(
        Long id, String name, String email,
        String userType, String subject, String message,
        String status, Instant createdAt
) {
}