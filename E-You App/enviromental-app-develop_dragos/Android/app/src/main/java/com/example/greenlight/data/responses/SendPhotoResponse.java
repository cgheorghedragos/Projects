package com.example.greenlight.data.responses;

import java.util.ArrayList;

public class SendPhotoResponse {
    ArrayList<String> data;
    String error;

    public SendPhotoResponse (ArrayList<String> data, String error) {
        this.error = error;this.data = data;
    }

    public  ArrayList<String> getResponse() {
        return data;
    }

    public void setResponse(ArrayList<String> response) {
        this.data = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
