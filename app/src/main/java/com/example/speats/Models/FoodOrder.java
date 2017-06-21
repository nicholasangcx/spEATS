package com.example.speats.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicholas on 13/6/2017.
 */

public class FoodOrder implements Parcelable {

    String sn;
    String name;
    String quantity;
    String price;

    public FoodOrder(String sn, String name, String quantity, String price) {
        this.sn = sn;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getSn() {
        return sn;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    protected FoodOrder(Parcel in) {
        sn = in.readString();
        name = in.readString();
        quantity = in.readString();
        price = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sn);
        dest.writeString(name);
        dest.writeString(quantity);
        dest.writeString(price);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FoodOrder> CREATOR = new Parcelable.Creator<FoodOrder>() {
        @Override
        public FoodOrder createFromParcel(Parcel in) {
            return new FoodOrder(in);
        }

        @Override
        public FoodOrder[] newArray(int size) {
            return new FoodOrder[size];
        }
    };
}
