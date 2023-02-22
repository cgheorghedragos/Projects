package com.example.greenlight.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.greenlight.R;
import com.example.greenlight.data.requests.UserModel;

public class SessionManager {
    private final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private final String FIRST_NAME = "FIRST_NAME";
    private final String LAST_NAME = "LAST_NAME";
    private final String GENDER = "GENDER";
    private final String BIRTHDAY = "BIRTHDAY";
    private final String PHOTO_PATH = "PHOTO_PATH";
    private final String  USERNAME = "USERNAME";
    private final String EMAIL = "EMAIL";
    private final SharedPreferences sharedPreferences;
    public SessionManager(Context context){
        this.sharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    public void saveAuthToken(String token){
         SharedPreferences.Editor editor =sharedPreferences.edit();
         editor.putString(ACCESS_TOKEN,token);
         editor.apply();
    }

    public String fetchAuthToken(){
        return sharedPreferences.getString(ACCESS_TOKEN, null);
    }

    public void saveUser(UserModel userModel) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FIRST_NAME, userModel.getFirst_name());
        editor.putString(LAST_NAME, userModel.getLast_name());
        editor.putString(USERNAME, userModel.getUsername());
        editor.putString(EMAIL, userModel.getEmail());
        editor.putString(GENDER, userModel.getGender());
        editor.putString(BIRTHDAY, userModel.getBirthday());
        editor.putString(PHOTO_PATH, userModel.getPhoto_path());

        editor.apply();
    }

    public UserModel fetchUser(){
        String first_name = sharedPreferences.getString(FIRST_NAME, null);
        String last_name = sharedPreferences.getString(LAST_NAME, null);
        String username = sharedPreferences.getString(USERNAME, null);
        String email = sharedPreferences.getString(EMAIL, null);
        String gender = sharedPreferences.getString(GENDER, null);
        String birthday = sharedPreferences.getString(BIRTHDAY, null);
        String photo_path = sharedPreferences.getString(PHOTO_PATH,null);

        UserModel userModel = new UserModel();
        userModel.setPhoto_path(photo_path);
        userModel.setFirst_name(first_name);
        userModel.setLast_name(last_name);
        userModel.setUsername(username);
        userModel.setEmail(email);
        userModel.setGender(gender);
        userModel.setBirthday(birthday);

        return userModel;
    }
}
