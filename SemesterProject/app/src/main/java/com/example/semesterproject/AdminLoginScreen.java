package com.example.semesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AdminLoginScreen extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText Email, Password;
    int i = 0; String email_str, password_str; int counter1, counter2 = 0;
    String email = "admin123@gmail.com";
    String password = "admin123";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_screen);
        Email = findViewById(R.id.txtemail);
        Password = findViewById(R.id.txtpassword);

        database = FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("AdminData");
        databaseReference.setValue("This is a database for Admin Data");

        String[] admin = {"Admin"};
        Map<String, Object> admindata = new HashMap<>();
        admindata.put("Email", email);
        admindata.put("Password", password);
        databaseReference.child(admin[i]).setValue(admindata);
    }

    public void moveToAdminDashboard(View view) {
        email_str =Email.getText().toString();
        password_str = Password.getText().toString();
        Query query1= FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/").getReference("AdminData").orderByChild("Email").equalTo(email_str);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //Toast.makeText(AdminLoginScreen.this, "Correct email", Toast.LENGTH_SHORT).show();
                    counter1 = 1;
                }
                else {
                    counter1 = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Query query2= FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/").getReference("AdminData").orderByChild("Password").equalTo(password_str);
        query2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                   // Toast.makeText(AdminLoginScreen.this, "Correct password", Toast.LENGTH_SHORT).show();
                    counter2 = 1;
                }
                else {
                    counter2 = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (counter1 == 1 && counter2 == 1)
        {
            Toast.makeText(AdminLoginScreen.this, "Login Successfull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AdminDashboard.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(AdminLoginScreen.this, "Invalid email and password", Toast.LENGTH_SHORT).show();
        }
        counter1 = 0;
        counter2 = 0;
    }

    public void moveToUserLogin(View view) {
        Intent intent = new Intent(this, UserLoginScreen.class);
        startActivity(intent);
    }

}