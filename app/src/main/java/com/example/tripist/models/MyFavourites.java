package com.example.tripist.models;

import java.io.Serializable;

public class MyFavourites extends Categories implements Serializable {
    public MyFavourites(String name, Double latitude, Double longitude, String image) {
        super(name, latitude, longitude, image);
    }
}
