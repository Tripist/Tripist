package com.example.tripist.models;

import java.io.Serializable;

public class MyLocations extends Places implements Serializable {
    public MyLocations(String name, Double latitude, Double longitude) {
        super(name, latitude, longitude);
    }
}
