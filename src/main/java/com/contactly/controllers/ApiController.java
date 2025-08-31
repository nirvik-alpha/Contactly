package com.contactly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactly.entites.Contact;
import com.contactly.services.ContactService;

@RestController  // returning json data 
@RequestMapping("/api") 
public class ApiController {

    @Autowired
    private ContactService contactService;


    @GetMapping("/contacts/{contactId}")
    public Contact geContact(@PathVariable String contactId) {
        // fetch contact from database by id and return as json data 
        
        return contactService.getById(contactId);


    }



}
