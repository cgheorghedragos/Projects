package com.example.greenlight.data.requests;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IncidentRequest {
    @SerializedName("incident_description")
    private String description;

    @SerializedName("photos")
    private List<String> photoPath;

    @SerializedName("marker_type")
    private String markerType;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("username")
    private String username;

    @SerializedName("incident_title")
    private String incident_title;
    public IncidentRequest(String incident_title,String username, String description, List<String> photoPath, String markerType, String latitude, String longitude) {
        this.description = description;
        this.photoPath = photoPath;
        this.markerType = markerType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.username = username;
        this.incident_title = incident_title;
    }

    public IncidentRequest() {

    }

    public String getIncident_title() {
        return incident_title;
    }

    public void setIncident_title(String incident_title) {
        this.incident_title = incident_title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(List<String> photoPath) {
        this.photoPath = photoPath;
    }

    public String getMarkerType() {
        return markerType;
    }

    public void setMarkerType(String markerType) {
        this.markerType = markerType;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "IncidentRequest{" +
                "description='" + description + '\'' +
                ", photoPath=" + photoPath +
                ", markerType='" + markerType + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", username='" + username + '\'' +
                ", incident_title='" + incident_title + '\'' +
                '}';
    }
}
