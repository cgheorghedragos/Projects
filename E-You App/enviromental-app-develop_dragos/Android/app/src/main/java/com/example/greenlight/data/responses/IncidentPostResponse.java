package com.example.greenlight.data.responses;

import com.google.gson.annotations.SerializedName;

public class IncidentPostResponse {
    @SerializedName("response")
    private String value;

    public IncidentPostResponse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
