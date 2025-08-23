package com.contactly.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.contactly.entites.Contact;
import com.contactly.entites.User;
import com.contactly.forms.ContactForm;
import com.contactly.forms.ContactSearchForm;
import com.contactly.helpers.AppConstants;
import com.contactly.helpers.Helper;
import com.contactly.helpers.Message;
import com.contactly.helpers.MessageType;
import com.contactly.services.ContactService;
import com.contactly.services.ImageService;
import com.contactly.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user/contacts")
public class ContactController {


     private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);


     @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

     @Autowired
    private UserService userService;



    // add contact page handler

    @RequestMapping("/add")
    public String addContactView(Model model) {

        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);

        return "user/add_contact";
    }

    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm ,BindingResult result, Authentication authentication, HttpSession session){


        // validation through binding result

        if(result.hasErrors()){
            
             result.getAllErrors().forEach(error -> logger.info(error.toString()));

             session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
               return "user/add_contact";
        }

         String username = Helper.getEmailOfLoggedInUser(authentication);

          User user = userService.getUserByEmail(username);

           String filename = UUID.randomUUID().toString();
           String fileURL = imageService.uploadImage(contactForm.getContactImage(), filename);

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavourite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

         contactService.save(contact);
        System.out.println(contactForm);


         session.setAttribute("message",
                Message.builder()
                        .content("You have successfully added a new contact")
                        .type(MessageType.green)
                        .build());

        

        return "redirect:/user/contacts/add";
    }

    // view contacts 

    @RequestMapping
     public String viewContacts(
         @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
             Model model,
            Authentication authentication) {

        // load all the user contacts
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact  = contactService.getByUser(user, page, size, sortBy, direction);

        model.addAttribute("pageContact", pageContact );

        // model.addAttribute("pageContact", pageContact);
         model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

         model.addAttribute("contactSearchForm", new ContactSearchForm());

        return "user/contacts";
    }


    @RequestMapping("/search")
    public  String saerchHandler(
    @ModelAttribute ContactSearchForm contactSearchForm,
     @RequestParam(value = "size", defaultValue = AppConstants.PAGE_SIZE + "") int size,
     @RequestParam(value = "page", defaultValue = "0") int page,
     @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
    @RequestParam(value = "direction", defaultValue = "asc") String direction, Model model,
    Authentication authentication
     ){

        logger.info("searching for {} with keyword {}",  contactSearchForm.getField(), contactSearchForm.getValue());

         var user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));
          Page<Contact> pageContact = null;
        if(contactSearchForm.getField().equalsIgnoreCase("name")){
             pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, direction , user);
        }
        else if(contactSearchForm.getField().equalsIgnoreCase("email")){
             pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, direction , user);
        }
        else if(contactSearchForm.getField().equalsIgnoreCase("phone")){
             pageContact = contactService.searchByPhoneNumber(contactSearchForm.getValue(), size, page, sortBy, direction , user);
        }

        

         model.addAttribute("contactSearchForm", contactSearchForm);

        model.addAttribute("pageContact", pageContact);

        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);

        return "user/search";
    }


}
