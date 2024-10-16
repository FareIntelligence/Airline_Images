package com.example.img_encoding_decoding.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images") // Map this class to the "images" collection in MongoDB
public class Image {

    @Id
    private String id; // This is the unique ID for MongoDB documents

    private String imageName; // Field for storing the image name

    private String image64Encoded; // Field for storing the Base64 encoded image

    // Constructors
    public Image() {}

    public Image(String imageName, String image64Encoded) {
        this.imageName = imageName;
        this.image64Encoded = image64Encoded;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImage64Encoded() {
        return image64Encoded;
    }

    public void setImage64Encoded(String image64Encoded) {
        this.image64Encoded = image64Encoded;
    }
}
