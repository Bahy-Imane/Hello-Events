package com.devart.helloevents.service;

import com.devart.helloevents.model.Contact;
import com.devart.helloevents.model.User;
import com.devart.helloevents.repository.BookingRepository;
import com.devart.helloevents.repository.ContactRepository;
import com.devart.helloevents.repository.EventRepository;
import com.devart.helloevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    public Contact createContact(Contact contact, User user) {
        contact.setUser(user);
        return contactRepository.save(contact);
    }
}
