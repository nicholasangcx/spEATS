package com.example.speats.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicholas on 13/6/2017.
 */

public class FoodItem implements Parcelable {

    String name;
    String quantity;
    String time;

    public FoodItem(String sn, String name, String quantity, String time) {
        this.name = name;
        this.quantity = quantity;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getTime() {
        return time;
    }

    protected FoodItem(Parcel in) {
        name = in.readString();
        quantity = in.readString();
        time = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(quantity);
        dest.writeString(time);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FoodItem> CREATOR = new Parcelable.Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };
}