package com.ahmed.jobportal.Contact.controller;

import com.ahmed.jobportal.Contact.service.ContactService;
import com.ahmed.jobportal.dto.ContactRequestDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping(version = "1.0")
    public ResponseEntity<String> saveContactMsg(@Valid @RequestBody ContactRequestDto request) {

        boolean isSaved = contactService.saveContact(request);

        if (isSaved) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Request processed successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Request processed failed");
    }

    @GetMapping
    public ResponseEntity<String> fetchOpenContacts(@RequestParam
                                                        @NotBlank(message = "can not be empty")
                                                        @Size(min = 4 ,
                                                                message = "should be greater than 4 characters")
                                                        String status){

        return ResponseEntity.ok("These are the contacts with the given status: " + status);
    }

}
