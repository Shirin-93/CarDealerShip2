package org.yearup.models;

import java.util.ArrayList;

public abstract class Contract
{
    private String type;
    private String date;
    private String name;
    private ArrayList<Vehicle>vehicleSold;

    public Contract(String type, String date,String name, ArrayList<Vehicle>vehicleSold )
    {
        this.type= type;
        this.date = date;
        this.name= name;
        this.vehicleSold = vehicleSold;
    }
    public abstract double getTotalPrice();
    public abstract double getMonthlyPayment();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Vehicle> getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(ArrayList<Vehicle> vehicleSold) {
        this.vehicleSold = vehicleSold;
    }
}
