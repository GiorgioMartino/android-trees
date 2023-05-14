package com.gmartino.trees.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Tree implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String name;

    @ColumnInfo
    private String scientificName;

    @ColumnInfo
    private String country;

    @ColumnInfo
    private String co2;

    public Tree(@NonNull String name, String scientificName, String country, String co2) {
        this.name = name;
        this.scientificName = scientificName;
        this.country = country;
        this.co2 = co2;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }
}
