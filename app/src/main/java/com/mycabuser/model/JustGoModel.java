package com.mycabuser.model;

public class JustGoModel {
    private String vehicalName;
    private String price;
    private String distance;
    private String time;
    private int vehicelImage;

    public String getVehicalName() {
        return vehicalName;
    }

    public void setVehicalName(String vehicalName) {
        this.vehicalName = vehicalName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getVehicelImage() {
        return vehicelImage;
    }

    public void setVehicelImage(int vehicelImage) {
        this.vehicelImage = vehicelImage;
    }

    public JustGoModel(String vehicalName, String price, String distance, String time, int vehicelImage) {
        this.vehicalName = vehicalName;
        this.price = price;
        this.distance = distance;
        this.time = time;
        this.vehicelImage = vehicelImage;
    }
}
