package com.android.app.touristguide.model;

import java.util.List;

public class POIResponse {
    // POIResponse
    private List<Result> results;
    private String status;
    public POIResponse(List<Result> results, String status) {
        this.results = results;
        this.status = status;
    }
    public List<Result> getResults() {
        return results;
    }
    public void setResults(List<Result> results) {
        this.results = results;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
