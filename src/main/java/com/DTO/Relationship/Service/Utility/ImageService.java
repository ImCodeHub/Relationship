package com.DTO.Relationship.Service.Utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

@Service
public class ImageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

/** to saved the image in Directory and return the image file name */

    public String saveUserImage(MultipartFile imageFile) throws IOException {
        if(imageFile.isEmpty()){
            throw new IOException("Empty file can not save.");
        }
//        created file name
        String imageFileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//                           = 464654967443146464_image.png
        Path directoryPath = Paths.get(uploadDir,imageFileName);
//        it will Enusure that directory exists.
        Files.createDirectories(directoryPath.getParent());
//        save the image
        Files.copy(imageFile.getInputStream(),directoryPath, StandardCopyOption.REPLACE_EXISTING);
        return imageFileName;
    }

/** to retrieve the image from Directory*/
    public String getEncodedImageFromDirectory(String imageFileName) throws IOException {
        Path directoryPath = Paths.get(uploadDir).resolve(imageFileName);
//                         = src/main/resources/user-image/464654967443146464_image.png
        byte[] imageBytes = Files.readAllBytes(directoryPath); // files read all buyts and return byte[] Arrays
        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);// converting bytes Arrays into encoded String
        return "data: image/jpeg:base64"+ encodedImage;
    }
}
