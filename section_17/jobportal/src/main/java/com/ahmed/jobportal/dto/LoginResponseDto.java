package com.ahmed.jobportal.dto;

public record LoginResponseDto(String message,UserDto user,String jwtToken) {
}
