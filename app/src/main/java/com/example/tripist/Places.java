package com.example.tripist;

import java.io.Serializable;

public class Places implements Serializable {

    public String name ;
    public Double latitude;
    public Double longitude;

    public Places(String name,Double latitude, Double longitude){
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
