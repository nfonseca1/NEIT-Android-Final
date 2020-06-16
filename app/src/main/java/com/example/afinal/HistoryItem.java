package com.example.afinal;

public class HistoryItem {
    int id;
    String time;
    String name;
    double fluid;
    String unit;
    String beverage;

    HistoryItem() {}

    HistoryItem(int id, String time, String name, double fluid, String unit, String beverage){
        this.id = id;
        this.time = time;
        this.name = name;
        this.fluid = fluid;
        this.unit = unit;
        this.beverage = beverage;
    }

    int getId() {
        return id;
    }
    String getTime() {
        return time;
    }
    String getName() {
        return name;
    }
    double getFluid() {
        return fluid;
    }
    String getUnit() {
        return unit;
    }
    String getBeverage() {
        return beverage;
    }
    void setId(int id) {
        this.id = id;
    }
    void setTime(String time) {
        this.time = time;
    }
    void setName(String name) {
        this.name = name;
    }
    void setFluid(double fluid) {
        this.fluid = fluid;
    }
    void setUnit(String unit) {
        this.unit = unit;
    }
    void setBeverage(String beverage) {
        this.beverage = beverage;
    }
}
