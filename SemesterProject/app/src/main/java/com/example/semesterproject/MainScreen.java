package com.example.semesterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }


    public void moveToUserLogin(View view) {
        Intent intent = new Intent(this, UserLoginScreen.class);
        startActivity(intent);
    }

    public void moveToAdminLogin(View view) {
        Intent intent = new Intent(this, AdminLoginScreen.class);
        startActivity(intent);
    }
}