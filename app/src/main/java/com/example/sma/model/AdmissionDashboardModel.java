package com.example.sma.model;

public class AdmissionDashboardModel {
    String title;
    Integer image;

    public AdmissionDashboardModel(String title, Integer image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public Integer getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
