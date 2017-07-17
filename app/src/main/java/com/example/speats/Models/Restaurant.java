package com.example.speats.Models;

/**
 * Created by joshl on 5/6/2017.
 */

import java.io.Serializable;
import java.util.HashMap;

public class Restaurant implements Serializable{

    private String name;
    private String overview;
    private HashMap<String, MenuItem> MainsMenu;
    private HashMap<String, MenuItem> SidesMenu;
    private HashMap<String, MenuItem> DrinksMenu;
    private String posterPath;
    private HashMap<String, Order> orderMaster;
    private Double numSeats;
    private Double occupiedSeats;

    public Restaurant(){

    }

    public Restaurant(String name, String overview, HashMap<String, MenuItem> mainsMenu, HashMap<String, MenuItem> sidesMenu, HashMap<String, MenuItem> drinksMenu, String posterPath, HashMap<String, Order> orderMaster, Double numSeats, Double occupiedSeats) {
        this.name = name;
        this.overview = overview;
        MainsMenu = mainsMenu;
        SidesMenu = sidesMenu;
        DrinksMenu = drinksMenu;
        this.posterPath = posterPath;
        this.orderMaster = orderMaster;
        this.numSeats = numSeats;
        this.occupiedSeats = occupiedSeats;
    }

    public Double getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(Double numSeats) {
        this.numSeats = numSeats;
    }

    public Double getOccupiedSeats() {
        return occupiedSeats;
    }

    public void setOccupiedSeats(Double occupiedSeats) {
        this.occupiedSeats = occupiedSeats;
    }

    public HashMap<String, Order> getOrderMaster() {
        return orderMaster;
    }

    public void setOrderMaster(HashMap<String, Order> orderMaster) {
        this.orderMaster = orderMaster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public HashMap<String, MenuItem> getMainsMenu() {
        return MainsMenu;
    }

    public void setMainsMenu(HashMap<String, MenuItem> mainsMenu) {
        MainsMenu = mainsMenu;
    }

    public HashMap<String, MenuItem> getSidesMenu() {
        return SidesMenu;
    }

    public void setSidesMenu(HashMap<String, MenuItem> sidesMenu) {
        SidesMenu = sidesMenu;
    }

    public HashMap<String, MenuItem> getDrinksMenu() {
        return DrinksMenu;
    }

    public void setDrinksMenu(HashMap<String, MenuItem> drinksMenu) {
        DrinksMenu = drinksMenu;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getAvailability() {
        Double d = (1 - getOccupiedSeats() /getNumSeats()) * 100;
        return d.intValue();
    }

}