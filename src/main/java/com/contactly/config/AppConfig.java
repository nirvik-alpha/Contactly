package com.contactly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {


    // @Bean
    // public Cloudinary cloudinary(){

    //    return new Cloudinary(

    //             ObjectUtils.asMap(
    //                     "cloud_name", "dfd",
    //                     "api_key", "apiKey",
    //                     "api_secret", "apiSecret")

    //     );
    // }


}
