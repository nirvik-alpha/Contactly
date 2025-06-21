package com.contactly.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {

    // user dashboard

    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }
    
 @RequestMapping(value="/profile")
    public String userProfile() {
        System.out.println("User profile");
        return "user/profile";
    }

    // user add contact page
    // user view contact  
    // user edit contact
    //user delete contact  



}
