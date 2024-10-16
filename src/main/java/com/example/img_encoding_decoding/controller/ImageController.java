package com.example.img_encoding_decoding.controller;

import com.example.img_encoding_decoding.entity.Image;
import com.example.img_encoding_decoding.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@RequestMapping("/api/images")
@CrossOrigin()  // Change the origin URL if your frontend runs elsewhere
public class ImageController {

    @Autowired
    private ImageService imageService;

    // Handle image upload
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("imageName") String imageName,
            @RequestParam("image") MultipartFile file) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            // Get the image bytes
            byte[] imageBytes = file.getBytes();

            // Save image and name to MongoDB
            imageService.saveImage(imageName, imageBytes);

            return ResponseEntity.ok("Image uploaded and saved to database successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Image upload failed.");
        }
    }

    // Fetch an image by its name
    @GetMapping("/get/{imageName}")
    public ResponseEntity<Image> getImageByName(@PathVariable String imageName) {
        Image image = imageService.getImageByName(imageName);
        if (image != null) {
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.status(404).body(null); // Image not found
        }
    }
    @GetMapping("/fetch/{imageName}")
    public ResponseEntity<ImageResponse> fetchImage(@PathVariable String imageName) {
        System.out.println("Looking for image: " + imageName);
        Image image = imageService.getImageByName(imageName);
        if (image != null) {
            String base64Image = image.getImage64Encoded();
            // Prepare the response with the correct media type
            ImageResponse response = new ImageResponse(base64Image);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) // Set content type to JSON
                    .body(response); // Return the response object
        } else {
            return ResponseEntity.status(404).body(new ImageResponse("Image not found"));
        }
    }

}
