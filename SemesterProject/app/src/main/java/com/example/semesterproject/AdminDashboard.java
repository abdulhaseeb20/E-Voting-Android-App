package com.example.semesterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
    }

    public void moveToAddParty(View view) {
        Intent intent = new Intent(this, AddParty.class);
        startActivity(intent);
    }

    public void moveToCheckVotes(View view) {
        Intent intent = new Intent(this, CheckVotes.class);
        startActivity(intent);
    }

    public void moveToAnnouncements(View view) {
        Intent intent = new Intent(this, Announcements.class);
        startActivity(intent);
    }

    public void moveToUpdateProfile(View view) {
        Intent intent = new Intent(this, UpdateProfile.class);
        startActivity(intent);
    }

    public void moveToMain(View view) {
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }
}