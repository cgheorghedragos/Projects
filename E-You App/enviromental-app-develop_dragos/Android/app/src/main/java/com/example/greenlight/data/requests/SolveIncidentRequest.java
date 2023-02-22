package com.example.greenlight.data.requests;

import java.util.ArrayList;

public class SolveIncidentRequest {
    private String incidentId;
    private ArrayList<String> usernames;

    public SolveIncidentRequest(String incidentId, ArrayList<String> usernames) {
        this.incidentId = incidentId;
        this.usernames = usernames;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }
}
