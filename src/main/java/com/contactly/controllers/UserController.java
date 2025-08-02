package com.contactly.controllers;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactly.helpers.Helper;


@Controller
@RequestMapping("/user")
public class UserController {

     private Logger logger = LoggerFactory.getLogger(UserController.class);
    // user dashboard

    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }
    
 @RequestMapping(value="/profile")
    public String userProfile(Authentication  authentication) {
       
       String name = Helper.getEmailOfLoggedInUser(authentication);
        
        logger.info("User profile accessed by: " + name);
        System.out.println("User profile");
        return "user/profile";
    }

    // user add contact page
    // user view contact  
    // user edit contact
    //user delete contact  



}
