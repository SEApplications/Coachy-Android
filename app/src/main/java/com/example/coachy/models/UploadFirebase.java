package com.example.coachy.models;

public class UploadFirebase {

    private String name;
    private String imageUrl;

    public UploadFirebase(){}

    public UploadFirebase(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
