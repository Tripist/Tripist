package com.example.tripist.models;

import java.io.Serializable;

public class Museums extends Places implements Serializable {
    public Museums(String name, Double latitude, Double longitude, String image) {
        super(name, latitude, longitude, image);
    }
}
