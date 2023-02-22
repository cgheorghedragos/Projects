package com.example.greenlight.data.models;

import android.graphics.drawable.Drawable;

public class MarkerModel {
    private String markerType;
    private String markerDescription;

    public MarkerModel(String markerType, String markerDescription) {
        this.markerType = markerType;
        this.markerDescription = markerDescription;
    }

    public String getMarkerType() {
        return markerType;
    }

    public void setMarkerType(String markerType) {
        this.markerType = markerType;
    }

    public String getMarkerDescription() {
        return markerDescription;
    }

    public void setMarkerDescription(String markerDescription) {
        this.markerDescription = markerDescription;
    }
}
