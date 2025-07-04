package com.contactly.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.contactly.entites.User;
import com.contactly.helpers.AppConstants;
import com.contactly.helpers.ResourceNotFoundException;
import com.contactly.repositories.UserRepo;
import com.contactly.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserRepo userRepo; // to save all the methods related to user

    @Autowired
    private PasswordEncoder passwordEncoder; // to encode the password


    private Logger logger =  LoggerFactory.getLogger(this.getClass());



    @Override
    public User saveUser(User user) {
       
        // user id : have to generate 

        String userId = UUID.randomUUID().toString(); // genrate random user id
        user.setUserId(userId);
        // password encode 
        // user.setPassword(userId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));  // encode the password


        // set the user role 

        
        user.setRoleList(List.of(AppConstants.ROLE_USER)); // set the default role as user

        return userRepo.save(user);

     }

    @Override
    public Optional<User> getUserById(String id) {
    
        return userRepo.findById(id);
        
    }

    @Override
    public Optional<User> updateUser(User user) {
   
       User user2 =  userRepo.findById(user.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
     
       // set & update user2 from user 
       
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        // save the updated user 
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);



    }

    @Override
    public void deleteUser(String id) {
  
        User user2 = userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
         userRepo.delete(user2);
    }

    @Override
    public boolean isUSerExists(String userId) {
   
        User user2 = userRepo.findById(userId).orElse(null);

        if(user2!=null){
            return true;
        }
        else{
            return false;
        }

   
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
   
       User user =  userRepo.findByEmail(email).orElseThrow(null);
       return user !=null   ?true :false;   
    }

    @Override
    public List<User> getAllUsers() {
   
        return userRepo.findAll();
   
    }





}
