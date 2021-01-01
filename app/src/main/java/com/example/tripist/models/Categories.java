package com.example.tripist.models;

import java.io.Serializable;

public class Categories implements Serializable {
    //Definition variables
    public String name ;
    public Double latitude;
    public Double longitude;
    public String image;

    // Constructor
    public Categories(String name, Double latitude, Double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    // Constructor
    public Categories(String name, Double latitude, Double longitude, String image) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }
    // Constructor
    public Categories(String name, String image) {
        this.name = name;
        this.image = image;
    }

    //get and set function
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() { return longitude; }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
