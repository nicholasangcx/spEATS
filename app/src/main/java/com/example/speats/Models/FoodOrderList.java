package com.example.speats.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nicholas on 19/6/2017.
 */

public class FoodOrderList implements Serializable, Parcelable {

    private ArrayList<FoodOrder> foodOrder;

    public FoodOrderList() {
    }

    public ArrayList<FoodOrder> getList() {
        return this.foodOrder;
    }

    public void add(FoodOrder item) {
        foodOrder.add(item);
    }

    public FoodOrder get(int position) {
        return foodOrder.get(position);
    }

    public void remove(int position) {
        foodOrder.remove(position);
    }

    public int size() {
        return foodOrder.size();
    }

    protected FoodOrderList(Parcel in) {
        if (in.readByte() == 0x01) {
            foodOrder = new ArrayList<FoodOrder>();
            in.readList(foodOrder, FoodOrder.class.getClassLoader());
        } else {
            foodOrder = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (foodOrder == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(foodOrder);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FoodOrderList> CREATOR = new Parcelable.Creator<FoodOrderList>() {
        @Override
        public FoodOrderList createFromParcel(Parcel in) {
            return new FoodOrderList(in);
        }

        @Override
        public FoodOrderList[] newArray(int size) {
            return new FoodOrderList[size];
        }
    };
}
