package com.example.tripist.models;

import java.io.Serializable;

public class Parks extends Places implements Serializable {
    public Parks(String name, Double latitude, Double longitude, String image) {
        super(name, latitude, longitude, image);
    }
}
