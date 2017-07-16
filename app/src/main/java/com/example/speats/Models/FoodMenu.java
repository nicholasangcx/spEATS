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
    String category;

    public FoodMenu() {

    }
    public FoodMenu(String id, String name, String price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() { return name; }

    public String getPrice() { return price; }

    public String getCategory() {
        return category;
    }

    protected FoodMenu(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readString();
        category = in.readString();
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
        dest.writeString(category);
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