package com.contactly.services;

import java.util.List;
import java.util.Optional;

import com.contactly.entites.User;

public interface UserService {


    // methods which are related to all user operations

    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);
    
    void deleteUser(String id);
    boolean isUSerExists(String userId);
    boolean isUserExistsByEmail(String email);

    List<User> getAllUsers();



}
