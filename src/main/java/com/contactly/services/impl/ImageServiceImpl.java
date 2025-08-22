package com.contactly.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.contactly.services.ImageService;

@Service
public class ImageServiceImpl implements ImageService {


    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {
     
        return "";
    }


}
