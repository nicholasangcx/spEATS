package com.example.speats.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicholas on 15/6/2017.
 */

public class FoodMenu implements Parcelable {

    String name;
    String price;

    public FoodMenu(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }

    public String getPrice() { return price; }

    protected FoodMenu(Parcel in) {
        name = in.readString();
        price = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
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