package com.contactly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.contactly.services.impl.SecurityCustomUserDetailService;

@Configuration

public class SecurityConfig {

    //user create & login

    // @Bean
    // public UserDetailsService userDetailsService(){
        

    //    UserDetails user1 =   User
    //    .withDefaultPasswordEncoder()
    //    .username("admin123")
    //    .password("admin123")
    //    .roles("ADMIN","USER")
    //    .build();

    //    UserDetails user2 =   User
    //    .withDefaultPasswordEncoder()
    //    .username("user123")
    //    .password("user123")
    //    .build();

    //     var inMemoryUserDetailsManager =  new InMemoryUserDetailsManager(user1);
    //     return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;




    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); // this has all the methods through which we can authenticate user
       
        // user details service object 

        daoAuthenticationProvider.setUserDetailsService(userDetailService);
      
        // password encoder object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{


        // configuration
        // urls configuration public or private 
        
        httpSecurity.authorizeHttpRequests(authorize ->{
            // authorize
            // .requestMatchers("/home").permitAll();

            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll(); // all other requests are allowed

        });

        // default form login 
        httpSecurity.formLogin(Customizer.withDefaults()); // this will enable form based login


       return httpSecurity.build();  // default security filter chain


    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // this is used to encode the password
    }








}
