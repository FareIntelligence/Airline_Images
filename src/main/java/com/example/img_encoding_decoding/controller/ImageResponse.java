package com.example.img_encoding_decoding.controller;

public class ImageResponse {
    private String baseImage;

    public ImageResponse(String baseImage) {
        this.baseImage = baseImage;
    }

    public String getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(String baseImage) {
        this.baseImage = baseImage;
    }
}
