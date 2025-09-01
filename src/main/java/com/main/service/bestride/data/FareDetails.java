package com.main.service.bestride.data;

public class FareDetails {
    private String origin;
    private String destination;
    private Double cost;
    private TravelMode mode;

    private App app;

    public FareDetails() {
    }

    public FareDetails(App app, String origin, String destination, Double cost, TravelMode mode ) {
        this.app = app;
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
        this.mode = mode;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public TravelMode getMode() {
        return mode;
    }

    public void setMode(TravelMode mode) {
        this.mode = mode;
    }


}
