package com.example.tripist.models;

import java.io.Serializable;

public class Places implements Serializable {

    public String name ;
    public Double latitude;
    public Double longitude;
    public String image;

    public Places(String name,Double latitude, Double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Places(String name, Double latitude, Double longitude, String image) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
    }

    public Places(String name, String image) {
        this.name = name;
        this.image = image;
    }

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

    public Double getLongitude() {
        return longitude;
    }

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
