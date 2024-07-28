package com.devart.helloevents.controller;

import com.devart.helloevents.model.Contact;
import com.devart.helloevents.model.User;
import com.devart.helloevents.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/consulter")
    public List<Contact> getAllContactRequests() {
        return contactService.getAllContact();
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/user/create")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact, @AuthenticationPrincipal User user) {
       Contact Contact1 = contactService.createContact(contact, user);
        return ResponseEntity.ok(Contact1);
    }
}
