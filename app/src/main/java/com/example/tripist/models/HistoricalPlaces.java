package com.example.tripist.models;

import java.io.Serializable;

public class HistoricalPlaces extends Categories implements Serializable {
    public HistoricalPlaces(String name, Double latitude, Double longitude, String image) {
        super(name, latitude, longitude, image);
    }
}
