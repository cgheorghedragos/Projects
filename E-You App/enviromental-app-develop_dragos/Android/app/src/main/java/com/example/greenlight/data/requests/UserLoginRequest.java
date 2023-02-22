package com.example.greenlight.data.requests;

public class UserLoginRequest {

    private final String username;
    private final String password;

    public UserLoginRequest(String Username, String Password) {
        username = Username;
        password = Password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isPasswordLengthGreaterThan5() {
        return getPassword().length() > 5;
    }

}
