package com.example.tripist.models;

import java.io.Serializable;

public class Squares extends Categories implements Serializable {
    public Squares(String name, Double latitude, Double longitude, String image) {
        super(name, latitude, longitude, image);
    }
}
