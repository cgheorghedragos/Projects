package com.example.greenlight.data.responses;

import com.example.greenlight.data.models.IncidentsModel;

import java.util.ArrayList;

public class IncidentGetResponse {
    private ArrayList<IncidentsModel> data;
    private String error;

    public IncidentGetResponse(ArrayList<IncidentsModel> data, String error) {
        this.data = data;
        this.error = error;
    }

    public ArrayList<IncidentsModel> getData() {
        return data;
    }

    public void setData(ArrayList<IncidentsModel> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
