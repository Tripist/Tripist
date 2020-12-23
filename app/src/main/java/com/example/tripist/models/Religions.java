package com.example.tripist.models;

import java.io.Serializable;

public class Religions extends Places implements Serializable {
    public Religions(String name, Double latitude, Double longitude, String image) {
        super(name, latitude, longitude, image);
    }
}
