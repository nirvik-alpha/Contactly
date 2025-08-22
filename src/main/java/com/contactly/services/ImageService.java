package com.contactly.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

     String uploadImage(MultipartFile contactImage, String filename);

}
