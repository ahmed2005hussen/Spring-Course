package com.ahmed.jobportal.auth;

import com.ahmed.jobportal.dto.LoginRequestDto;
import com.ahmed.jobportal.dto.LoginResponseDto;
import com.ahmed.jobportal.dto.UserDto;
import com.ahmed.jobportal.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/login/public" , version = "1.0")
    public ResponseEntity<LoginResponseDto> apiLogin(@RequestBody LoginRequestDto request) {
        try {
            var result = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                    request.username(), request.password()
            ));

            // generate JWT token
            String jwtToken = jwtUtil.generateJwtToken(result);
            return ResponseEntity.ok().body(
                    new LoginResponseDto(
                            HttpStatus.OK.getReasonPhrase(), new UserDto(), jwtToken)
            );
        } catch (BadCredentialsException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED,
                    "Invalid username or password");
        } catch (AuthenticationException ex) {
            return buildErrorResponse(HttpStatus.UNAUTHORIZED,
                    "Authentication failed");
        } catch (Exception ex) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred");
        }
    }


    private ResponseEntity<LoginResponseDto> buildErrorResponse(HttpStatus httpStatus, String message) {

        return ResponseEntity.status(httpStatus)
                .body(new LoginResponseDto(message, null, null));
    }
}
