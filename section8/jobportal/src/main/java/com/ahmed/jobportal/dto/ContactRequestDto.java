package com.ahmed.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.ahmed.jobportal.entity.Contact}
 */
public record ContactRequestDto(

        @NotBlank(message = "Email can not be empty")
        @Email(message = "Invalid email address")
        String email,

        @NotBlank(message = "Message can not be empty")
        @Size(min = 5, max = 500, message = "Message must be between 5 and 500 characters")
        String message,

        @Size(min = 5, max = 30, message = "Name must be between 5 and 30 characters")
        @NotBlank(message = "Name can not be empty")
        String name,

        @Size(min = 5, max = 150, message = "Subject must be between 5 and 30 characters")
        @NotBlank(message = "Subject can not be empty")
        String subject,

        @NotBlank(message = "User Type can not be empty")
        @Pattern(regexp = "Jop Seeker|Employer|Other",
                message = "User Type must be one of: Jop Seeker, Employer, Other")
        String userType

) implements Serializable {
}