package com.example.sma.model;

public class Attendance {
    String name;
    Boolean isPresent;
    String image;
    String rollNumber;


    public Attendance(String name, Boolean isPresent, String image, String rollNumber) {
        this.name = name;
        this.isPresent = isPresent;
        this.image = image;
        this.rollNumber = rollNumber;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }


    public Boolean getPresent() {
        return isPresent;
    }


    public String getImage() {
        return image;
    }
}
