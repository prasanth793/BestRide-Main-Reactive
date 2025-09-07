package com.main.service.bestride.data;


public class Distance {
    private String text;
    private Double value;

    public Distance(String text, Double value) {
        this.text = text;
        this.value = value;
    }

    public Distance() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
