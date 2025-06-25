package com.contactly.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.contactly.entites.Providers;
import com.contactly.entites.User;
import com.contactly.helpers.AppConstants;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component  // to autowire or use bean 
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler{


    Logger logger= LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);
    
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
     
               logger.info("OAuthAuthenticationSuccessHandler");
                
               DefaultOAuth2User user =(DefaultOAuth2User)authentication.getPrincipal();
               // data save to database

                // logger.info(user.getName());
               
                // user.getAttributes().forEach((key,value)->{
                //     logger.info("{}=>{}",key,value);
                // });

                    String email = user.getAttribute("email").toString();
                    String name = user.getAttribute("name").toString();
                    String picture = user.getAttribute("picture").toString();

                         User user1 = new User();
                         user1.setEmail(email);
                         user1.setName(name);
                         user1.setProfilePic(picture);
                         user1.setPassword("password");
                         user1.setUserId(UUID.randomUUID().toString());
                         user1.setProvider(Providers.GOOGLE);
                         user1.setEnabled(true);

                         user1.setEmailVerified(true);
                         user1.setProviderUserId(user.getName());
                         user1.setRoleList(List.of(AppConstants.ROLE_USER));
                         user1.setAbout("This account is created using google..");


               new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");

            }


}
