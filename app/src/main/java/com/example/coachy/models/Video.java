package com.example.coachy.models;

import java.io.Serializable;
import java.util.List;

public class Video implements Serializable {

    private String captured;
    private String type;
    private String name;
    private List<String> purchases;
    private String videoUrl;

    public Video() {
    }

    public Video(String captured, String type, String name, String videoUrl) {
        this.captured = captured;
        this.type = type;
        this.name = name;
        this.videoUrl = videoUrl;
    }

    public Video(String captured, String type, String name, List<String> purchases, String videoUrl) {
        this.captured = captured;
        this.type = type;
        this.name = name;
        this.purchases = purchases;
        this.videoUrl = videoUrl;
    }

    public String getCaptured() {
        return captured;
    }

    public void setCaptured(String captured) {
        this.captured = captured;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<String> purchases) {
        this.purchases = purchases;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "Video{" +
                "captured='" + captured + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", purchases=" + purchases +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
