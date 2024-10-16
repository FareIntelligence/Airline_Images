package com.example.img_encoding_decoding.repository;

import com.example.img_encoding_decoding.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    // Custom query method to find image by name if needed
    Image findByImageName(String imageName);
}

