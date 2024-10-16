package com.example.img_encoding_decoding.service;

import com.example.img_encoding_decoding.entity.Image;
import com.example.img_encoding_decoding.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    // Save image to MongoDB after converting it to Base64
    public Image saveImage(String imageName, byte[] imageBytes) {
        // Convert image to Base64 string
        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

        // Create a new Image object
        Image image = new Image(imageName, encodedImage);

        // Save to MongoDB and return the saved entity
        return imageRepository.save(image);
    }

    // Retrieve an image by name
    public Image getImageByName(String imageName) {
        return imageRepository.findByImageName(imageName);
    }
}
