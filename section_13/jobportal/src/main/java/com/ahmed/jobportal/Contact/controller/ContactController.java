package com.ahmed.jobportal.Contact.controller;

import com.ahmed.jobportal.Contact.service.ContactService;
import com.ahmed.jobportal.dto.ContactRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping(path = "/public",version = "1.0")
    public ResponseEntity<String> saveContactMsg(@Valid @RequestBody ContactRequestDto request) {

        boolean isSaved = contactService.saveContact(request);

        if (isSaved) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Request processed successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Request processed failed");
    }

}
