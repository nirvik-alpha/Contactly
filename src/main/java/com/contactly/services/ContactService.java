package com.contactly.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.contactly.entites.Contact;
import com.contactly.entites.User;

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
    Page<Contact> searchByName(String nameKeyword , int size, int page, String sortBy, String order , User user );

    Page<Contact> searchByEmail(String emailKeyword , int size, int page, String sortBy, String order, User user );

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword , int size, int page, String sortBy, String order , User user);

    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user , int page , int size ,String sortField, String sortDirection);



}
