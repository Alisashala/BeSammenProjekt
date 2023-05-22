package com.example.besammenprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UserModel extends AppCompatActivity {
    private String userEmail;
    private String userPassword;
    private String userId;

    public UserModel (String userEmail, String userId, String userPassword) {
        this.userEmail = userEmail;
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_model);
    }
}