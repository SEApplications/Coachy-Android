package com.example.coachy.models;

import java.io.Serializable;
import java.util.List;

public class Coach implements Serializable {

    private String id;
    private String fullName;
    private int age;
    private String description;
    private String phone;
    private String seniority;
    private List<String> specialize;
    private String profileImage;
    private String diploma;
//    private List<Video> videos;
    private String video;

    public Coach() {
    }



    public Coach(String id, String fullName, int age, String description, String phone, String seniority, String profileImage) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.description = description;
        this.phone = phone;
        this.seniority = seniority;
//        this.specialize = specialize;
        this.profileImage = profileImage;
//        this.diploma = diploma;
//        this.videos = videos;
    }

    public Coach(String id, String fullName, int age, String description, String phone, String seniority, List<String> specialize, String profileImage, String diploma, String video) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.description = description;
        this.phone = phone;
        this.seniority = seniority;
        this.specialize = specialize;
        this.profileImage = profileImage;
        this.diploma = diploma;
        this.video = video;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public List<String> getSpecialize() {
        return specialize;
    }

    public void setSpecialize(List<String> specialize) {
        this.specialize = specialize;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

//    public List<Video> getVideos() {
//        return videos;
//    }
//
//    public void setVideos(List<Video> videos) {
//        this.videos = videos;
//    }

    @Override
    public String toString() {
        return "Coach{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", seniority='" + seniority + '\'' ;
//                ", specialize=" + specialize +
//                ", profileImage='" + profileImage + '\'' +
//                ", diploma='" + diploma + '\'' +
//                ", videos=" + videos +
//                '}';
    }
}
