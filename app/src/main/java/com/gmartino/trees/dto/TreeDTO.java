package com.gmartino.trees.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TreeDTO implements Parcelable {

    private String name;
    private String scientificName;
    private String country;
    private String co2;

    public TreeDTO(String name, String scientificName, String country, String co2) {
        this.name = name;
        this.scientificName = scientificName;
        this.country = country;
        this.co2 = co2;
    }

    protected TreeDTO(Parcel in) {
        name = in.readString();
        scientificName = in.readString();
        country = in.readString();
        co2 = in.readString();
    }

    public static final Creator<TreeDTO> CREATOR = new Creator<TreeDTO>() {
        @Override
        public TreeDTO createFromParcel(Parcel in) {
            return new TreeDTO(in);
        }

        @Override
        public TreeDTO[] newArray(int size) {
            return new TreeDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(scientificName);
        dest.writeString(country);
        dest.writeString(co2);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
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
