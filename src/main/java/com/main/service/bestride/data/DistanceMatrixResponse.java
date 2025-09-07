package com.main.service.bestride.data;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DistanceMatrixResponse {
    @JsonProperty("destination_addresses")
    private List<String> destination;

    @JsonProperty("origin_addresses")
    private List<String> origin;

    private List<Row> rows;
    private String status;

    public List<String> getDestination() {
        return destination;
    }

    public void setDestination(List<String> destination) {
        this.destination = destination;
    }

    public List<String> getOrigin() {
        return origin;
    }

    public void setOrigin(List<String> origin) {
        this.origin = origin;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

