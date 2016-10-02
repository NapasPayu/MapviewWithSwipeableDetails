package com.napas.mapswipedetail;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Payu on 2/10/16.
 */

public class Merchant implements Parcelable {

    private String name;
    private String description;
    private double locLat;
    private double locLong;
    private String imageUrl;

    public Merchant(String name, String description, double locLat, double locLong, String imageUrl) {
        this.name = name;
        this.description = description;
        this.locLat = locLat;
        this.locLong = locLong;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLocLat() {
        return locLat;
    }

    public void setLocLat(double locLat) {
        this.locLat = locLat;
    }

    public double getLocLong() {
        return locLong;
    }

    public void setLocLong(double locLong) {
        this.locLong = locLong;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeDouble(this.locLat);
        dest.writeDouble(this.locLong);
        dest.writeString(this.imageUrl);
    }

    protected Merchant(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.locLat = in.readDouble();
        this.locLong = in.readDouble();
        this.imageUrl = in.readString();
    }

    public static final Creator<Merchant> CREATOR = new Creator<Merchant>() {
        @Override
        public Merchant createFromParcel(Parcel source) {
            return new Merchant(source);
        }

        @Override
        public Merchant[] newArray(int size) {
            return new Merchant[size];
        }
    };
}
