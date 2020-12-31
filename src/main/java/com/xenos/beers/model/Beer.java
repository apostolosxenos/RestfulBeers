package com.xenos.beers.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "beers")
@DynamicUpdate
public class Beer {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid", columnDefinition = "BINARY(16)")
    private UUID uuid;

    @Column(name = "brand")
    private String brand;

    @Column(name = "appearance")
    private String appearance;

    @Column(name = "aroma")
    private String aroma;

    @Column(name = "alcohol")
    private float alcohol;

    public Beer() {
    }

    public Beer(String brand, String appearance, String aroma, float alcohol) {
        this.brand = brand;
        this.appearance = appearance;
        this.aroma = aroma;
        this.alcohol = alcohol;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getBrand() {
        return brand;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getAroma() {
        return aroma;
    }

    public float getAlcohol() {
        return alcohol;
    }

    public void setUuid(UUID beerId) {
        this.uuid = beerId;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public void setAroma(String aroma) {
        this.aroma = aroma;
    }

    public void setAlcohol(float alcohol) {
        this.alcohol = alcohol;
    }

    @Override
    public String toString() {
        return "Beer [UUID=" + uuid + ", Brand=" + brand + ", Appearance=" + appearance + ", Aroma=" + aroma + ", Alcohol=" + alcohol + "]";
    }
}
