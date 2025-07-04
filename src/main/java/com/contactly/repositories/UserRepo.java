package com.contactly.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactly.entites.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{

    // extra methods db related operations
    // custom query methods
    // custom finder methods 

    Optional<User> findByEmail(String email); // find user by email(field)
    Optional<User> findByEmailAndPassword(String email , String pasword);
    

}
