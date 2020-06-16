package com.example.afinal;

public class Goal {
    int id;
    double goal;
    double progress;
    String unit;

    Goal(int id, double goal, double progress, String unit){
        this.id = id;
        this.goal = goal;
        this.progress = progress;
        this.unit = unit;
    }

    int getId() {
        return id;
    }
    double getGoal() {
        return goal;
    }
    double getProgress() {
        return progress;
    }
    String getUnit() {
        return unit;
    }
    void setId(int id){
        this.id = id;
    }
    void setGoal(double goal){
        this.goal = goal;
    }
    void setProgress(double progress){
        this.progress = progress;
    }
    void setUnit(String unit){
        this.unit = unit;
    }
}
