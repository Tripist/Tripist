package com.example.tripist.models;

import java.io.Serializable;

public class BazaarMarkets extends Places implements Serializable {
    public BazaarMarkets(String name, Double latitude, Double longitude, String image) {
        super(name, latitude, longitude, image);
    }
}
