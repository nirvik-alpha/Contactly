package com.contactly.controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.contactly.entites.User;
import com.contactly.helpers.Helper;
import com.contactly.services.UserService;

@ControllerAdvice
public class RootController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(RootController.class);
    
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model , Authentication authentication){

        if(authentication ==null){
            return ;
        }

        System.out.println("Adding logged in user information to model");;
         String name = Helper.getEmailOfLoggedInUser(authentication);
        
        logger.info("User profile accessed by: " + name);


        User user = userService.getUserByEmail(name);
        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getEmail());        
        model.addAttribute("loggedInUser", user);

    }




}
