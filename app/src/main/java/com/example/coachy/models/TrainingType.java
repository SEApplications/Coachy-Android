package com.example.coachy.models;

import java.io.Serializable;
import java.util.List;

public class TrainingType implements Serializable {

    private int position;
    private Integer trainingImage;
    private String name;
    private List<Coach> coaches;

    public TrainingType() {
    }

    public TrainingType(int position, Integer trainingImage, String name) {
        this.position = position;
        this.trainingImage = trainingImage;
        this.name = name;
    }

    public TrainingType(int position, Integer trainingImage, String name, List<Coach> coaches) {
        this.position = position;
        this.trainingImage = trainingImage;
        this.name = name;
        this.coaches = coaches;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Integer getTrainingImage() {
        return trainingImage;
    }

    public void setTrainingImage(Integer trainingImage) {
        this.trainingImage = trainingImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches) {
        this.coaches = coaches;
    }

    @Override
    public String toString() {
        return "TrainingType{" +
                "position=" + position +
                ", trainingImage=" + trainingImage +
                ", name='" + name + '\'' +
                ", coaches=" + coaches +
                '}';
    }
}
