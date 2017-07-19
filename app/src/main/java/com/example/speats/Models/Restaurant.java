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
    private HashMap<String, ByItem> byItemMaster;
    private int orderNumSoFar;
    private int smallTable;
    private int mediumTable;
    private int largeTable;
    private HashMap<String, Integer> map;

    public Restaurant(){

    }

    public Restaurant(String name, String overview, HashMap<String, MenuItem> mainsMenu, HashMap<String, MenuItem> sidesMenu, HashMap<String, MenuItem> drinksMenu, String posterPath, HashMap<String, Order> orderMaster, HashMap<String, ByItem> byItemMaster, int orderNumSoFar, int smallTable, int mediumTable, int largeTable, HashMap<String, Integer> map) {
        this.name = name;
        this.overview = overview;
        MainsMenu = mainsMenu;
        SidesMenu = sidesMenu;
        DrinksMenu = drinksMenu;
        this.posterPath = posterPath;
        this.orderMaster = orderMaster;
        this.byItemMaster = byItemMaster;
        this.orderNumSoFar = orderNumSoFar;
        this.smallTable = smallTable;
        this.mediumTable = mediumTable;
        this.largeTable = largeTable;
        this.map = map;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    public int getSmallTable() {
        return smallTable;
    }

    public void setSmallTable(int smallTable) {
        this.smallTable = smallTable;
    }

    public int getMediumTable() {
        return mediumTable;
    }

    public void setMediumTable(int mediumTable) {
        this.mediumTable = mediumTable;
    }

    public int getLargeTable() {
        return largeTable;
    }

    public void setLargeTable(int largeTable) {
        this.largeTable = largeTable;
    }

    public int getOrderNumSoFar() {
        return orderNumSoFar;
    }

    public void setOrderNumSoFar(int orderNumSoFar) {
        this.orderNumSoFar = orderNumSoFar;
    }

    public void incOrderNumSoFar() {
        if (orderNumSoFar < 9999) {
            orderNumSoFar++;
        } else {
            orderNumSoFar = 1;
        }
    }

    public HashMap<String, ByItem> getByItemMaster() {
        return byItemMaster;
    }

    public void setByItemMaster(HashMap<String, ByItem> byItemMaster) {
        this.byItemMaster = byItemMaster;
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

    public int getAvailability(String time) {
        return (int) ((1 - ((double)(map.get("large" + time) + map.get("medium" + time) + map.get("small" + time)) /(double)(largeTable + mediumTable + smallTable))) * 100);

    }

    public int getOccupiedSmall(String time) {
        return map.get("small" + time);
    }

    public int getOccupiedMedium(String time) {
        return map.get("medium" + time);
    }

    public int getOccupiedLarge(String time) {
        return map.get("large" + time);
    }

    public void incSmall(String time){
        map.put("small" + time, map.get("small" + time) + 1);
    }

    public void incMedium(String time){
        map.put("medium" + time, map.get("medium" + time) + 1);
    }

    public void incLarge(String time) {
        map.put("large" + time, map.get("large" + time) + 1);
    }
}
