package com.main.service.bestride.data;

import java.util.List;

public class Row {
    private List<Element> elements;

    public Row(List<Element> elements) {
        this.elements = elements;
    }

    public Row() {
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
