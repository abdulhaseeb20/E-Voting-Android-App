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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    EditText new_email, new_password;
    String str_email, str_password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        new_email = findViewById(R.id.txtemail);
        new_password = findViewById(R.id.txtpassword);

    }

    public void moveToUpdataData(View view) {
        str_email =new_email.getText().toString();
        str_password = new_password.getText().toString();
        Query query = FirebaseDatabase.getInstance().getReference("AdminData");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String key = snapshot.getKey();
                        FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/")
                                .getReference("AdminData").child(key).child("Email").setValue(str_email);
                        FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/")
                                .getReference("AdminData").child(key).child("Password").setValue(str_password);
                    }
                    new_email.setText("");
                    new_password.setText("");
                    Toast.makeText(UpdateProfile.this, "Login data updated successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(UpdateProfile.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(this, AdminLoginScreen.class);
        startActivity(intent);
    }
}