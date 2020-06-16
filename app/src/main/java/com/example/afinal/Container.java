package com.example.afinal;

public class Container {
    int id;
    String name;
    double capacity;
    String unit;

    Container(){}

    Container(int id, String name, double capacity, String unit){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.unit = unit;
    }

    int getId() {
        return id;
    }
    String getName() {
        return name;
    }
    double getCapacity() {
        return capacity;
    }
    String getUnit() {
        return unit;
    }
    void setId(int id){
        this.id = id;
    }
    void setName(String name){
        this.name = name;
    }
    void setCapacity(double capacity){
        this.capacity = capacity;
    }
    void setUnit(String unit){
        this.unit = unit;
    }
}
