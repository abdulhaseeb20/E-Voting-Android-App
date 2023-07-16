package com.example.semesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserLoginScreen extends AppCompatActivity
{
    EditText Cnic, Password;
    String cnic_str, password_str; int counter1, counter2 = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_screen);
        Cnic = findViewById(R.id.txtemail);
        Password = findViewById(R.id.txtpassword);
    }

    public void moveToLoginForm(View view)
    {
        Intent intent = new Intent(this, UserSignUpForm.class);
        startActivity(intent);
    }

    public void moveToCastVote(View view)
    {
        cnic_str = Cnic.getText().toString();
        password_str = Password.getText().toString();
        DataHolder.getInstance().setTextValue(cnic_str);

            DatabaseReference reference= FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/").getReference("VoterData");
            reference.child("voter").child(cnic_str).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                @Override
                public void onSuccess(DataSnapshot dataSnapshot) {
                    if((dataSnapshot.child("CNIC").getValue().toString()).equals(cnic_str) &&(dataSnapshot.child("Password").getValue().toString()).equals(password_str) ){
                        Toast.makeText(UserLoginScreen.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserLoginScreen.this, CastVote.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(UserLoginScreen.this, "Incorrect Password or email", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}