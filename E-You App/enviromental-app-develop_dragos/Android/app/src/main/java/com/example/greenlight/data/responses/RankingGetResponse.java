package com.example.greenlight.data.responses;

import com.example.greenlight.data.requests.UserModel;

import java.util.ArrayList;

public class RankingGetResponse {
    private ArrayList<UserModel> data;
    private String error;

    public RankingGetResponse(ArrayList<UserModel> data, String error) {
        this.data = data;
        this.error = error;
    }

    public ArrayList<UserModel> getData() {
        return data;
    }

    public void setData(ArrayList<UserModel> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
