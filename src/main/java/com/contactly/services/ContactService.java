package com.contactly.services;

import java.util.List;

import com.contactly.entites.Contact;

public interface ContactService {

    // save contact

    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    // get contact by id 
    Contact getById(String id);

    // delete contact by id
    void delete(String id); 

    // search contacts
    List<Contact> search(String name , String email , String phoneNumber);

    List<Contact> getByUserId(String userId);

    



}
