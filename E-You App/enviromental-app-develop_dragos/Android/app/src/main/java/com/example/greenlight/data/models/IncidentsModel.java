package com.example.greenlight.data.models;

import com.example.greenlight.data.requests.IncidentRequest;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IncidentsModel {
    private String documentId;
    private IncidentRequest incident;

    public IncidentsModel(String documentId, IncidentRequest incident) {
        this.documentId = documentId;
        this.incident = incident;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public IncidentRequest getIncident() {
        return incident;
    }

    public void setIncident(IncidentRequest incident) {
        this.incident = incident;
    }

    @Override
    public String toString() {
        return "IncidentsModel{" +
                "documentId='" + documentId + '\'' +
                ", incident=" + incident +
                '}';
    }
}
