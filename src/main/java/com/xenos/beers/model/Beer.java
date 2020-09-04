package com.xenos.beers.model;

import java.util.UUID;

public class Beer {

    //attributes
    private UUID uuid;
    private String brand;
    private String appearance;
    private String aroma;
    private float alcohol;

    //constructor
    public Beer(
            UUID uuid,
            String brand,
            String appearance,
            String aroma,
            float alcohol) {
        this.uuid = uuid;
        this.brand = brand;
        this.appearance = appearance;
        this.aroma = aroma;
        this.alcohol = alcohol;
    }

    //getters and setters
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getAroma() {
        return aroma;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(float alcohol) {
        this.alcohol = alcohol;
    }
}
