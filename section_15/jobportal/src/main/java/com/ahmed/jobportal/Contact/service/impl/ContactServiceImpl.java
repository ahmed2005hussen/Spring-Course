package com.ahmed.jobportal.Contact.service.impl;

import com.ahmed.jobportal.Contact.service.ContactService;
import com.ahmed.jobportal.dto.ContactRequestDto;
import com.ahmed.jobportal.entity.Contact;
import com.ahmed.jobportal.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private Contact transformToEntity(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
//
//        contact.setCreatedAt(Instant.now());
//        contact.setCreatedBy("System");
        contact.setStatus("NEW");
        return contact;
    }

    @Override
    public boolean saveContact(ContactRequestDto request) {

        Contact c = contactRepository.save(transformToEntity(request));

        if (c != null && c.getId() != null) {
            return true;
        }
        return false;
    }
}
