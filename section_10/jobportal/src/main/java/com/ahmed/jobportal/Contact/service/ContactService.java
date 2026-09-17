package com.ahmed.jobportal.Contact.service;

import com.ahmed.jobportal.dto.ContactRequestDto;

public interface ContactService {
    boolean saveContact(ContactRequestDto request);
}
