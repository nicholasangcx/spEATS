package com.example.speats.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicholas on 15/6/2017.
 */

public class FoodMenu implements Parcelable {

    String id;
    String name;
    String price;
    String description;
    String posterPath;

    public FoodMenu() {

    }
    public FoodMenu(String id, String name, String price, String description, String posterPath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.posterPath = posterPath;
    }

    public String getId() {
        return id;
    }

    public String getName() { return name; }

    public String getPrice() { return price; }

    public String getdescription() {
        return description;
    }

    public String getPosterPath() {
        return posterPath;
    }

    protected FoodMenu(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readString();
        description = in.readString();
        posterPath = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(description);
        dest.writeString(posterPath);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FoodMenu> CREATOR = new Parcelable.Creator<FoodMenu>() {
        @Override
        public FoodMenu createFromParcel(Parcel in) {
            return new FoodMenu(in);
        }

        @Override
        public FoodMenu[] newArray(int size) {
            return new FoodMenu[size];
        }
    };
}