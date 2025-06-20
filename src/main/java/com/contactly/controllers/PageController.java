package com.contactly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contactly.entites.User;
import com.contactly.forms.UserForm;
import com.contactly.helpers.Message;
import com.contactly.helpers.MessageType;
import com.contactly.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class PageController {

    @Autowired  // autowired or constructor injection 
    private UserService userService;




    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page handler");

        // sending data to view

      

        return "home";
    
    }

    // about 
    @RequestMapping("/about")
    public String aboutPage(){
        System.out.println("About page handler");
        return "about";
    }

  @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page handler");
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }
    
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }
    

    @GetMapping("/register")
    public String register(Model model) {

        
        UserForm userForm = new UserForm();
        
        model.addAttribute("userForm", userForm); // send the userForm object to model which will be used in the register view 




        return "register";
    }
    
    // processing the register form data 

    @RequestMapping(value = "/do-register" , method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm ,BindingResult rBindingResult, HttpSession session){

        // fetch form data

        // validate form data
        if(rBindingResult.hasErrors()){
             return "register"; // return to register page with error message
        }


        // save to database
        
        // userSerivce

        //  will give a new user object
    //    User user =  User.builder()
    //    .name(userForm.getName())
    //    .email(userForm.getEmail())
    //    .password(userForm.getPassword())
    //    .about(userForm.getAbout())
    //    .phoneNumber(userForm.getPhoneNumber())
    //    .profilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.dreamstime.com%2Femployee-avatar-profile-photo-icon-vector-default-businessman-image-image280771777&psig=AOvVaw2NhVf_qwNc16EiGVN8nuwM&ust=1750181039815000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCNDygsu69o0DFQAAAAAdAAAAABAe")
    //    .build();   


       User user =  new User();
       user.setName(userForm.getName());
       user.setEmail(userForm.getEmail());
         user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(true);
    
        user.setProfilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.dreamstime.com%2Femployee-avatar-profile-photo-icon-vector-default-businessman-image-image280771777&psig=AOvVaw2NhVf_qwNc16EiGVN8nuwM&ust=1750181039815000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCNDygsu69o0DFQAAAAAdAAAAABAe");

       User savedUser =  userService.saveUser(user);
        System.out.println("User saved: " + savedUser);

        // message show success ( use session flash attribute or model attribute to show message in view)
        
       Message message =  Message.builder().content("registerd successfully").type(MessageType.green).build();
        session.setAttribute("message", message);


        // redirection login 


        return "redirect:/register";
    }





}
