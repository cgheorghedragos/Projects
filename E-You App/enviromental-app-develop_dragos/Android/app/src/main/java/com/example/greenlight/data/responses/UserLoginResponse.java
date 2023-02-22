package com.example.greenlight.data.responses;

import com.example.greenlight.data.requests.UserModel;

public class UserLoginResponse {
    private String jwt;
    private UserModel data;
    private String error;

    public UserLoginResponse(String jwt, UserModel user, String error) {
        this.jwt = jwt;
        this.data = user;
        this.error = error;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserModel getUser() {
        return data;
    }

    public void setUser(UserModel user) {
        this.data = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
