package com.contactly.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    public static void removeMessage(){
        try{
            System.out.println("Removing message from session");

             // Get the current HTTP session using Spring's request context holder
            HttpSession session = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes())  // Get current request context
                .getRequest()                                  // Get the actual HttpServletRequest
                .getSession();                                 // Get the HttpSession from it


        session.removeAttribute("message");

        }catch(Exception e){
           System.out.println("Error removing message from session: " + e.getMessage());
        
    }

    }

}
