package com.contactly.controllers;

import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactly.entites.User;
import com.contactly.helpers.Helper;
import com.contactly.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

     private Logger logger = LoggerFactory.getLogger(UserController.class);
    // user dashboard

    @Autowired
    private UserService userService;




    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }
    
 @RequestMapping(value="/profile")
    public String userProfile(Model model , Authentication  authentication) {
       
    //    String name = Helper.getEmailOfLoggedInUser(authentication);
        
    //     logger.info("User profile accessed by: " + name);


    //     User user = userService.getUserByEmail(name);
    //     System.out.println(user.getName());
    //     System.out.println(user.getEmail());
        
    //     model.addAttribute("loggedInUser", user);



        return "user/profile";
    }

    // user add contact page
    // user view contact  
    // user edit contact
    //user delete contact  



}
