package com.example.semesterproject;

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

public class UserSignUpForm extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText name, email, cnic, password, mobileno, city;
    int i = 0;  Boolean flag = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup_form);

        name = findViewById(R.id.txtname);
        email= findViewById(R.id.txtemail);
        cnic = findViewById(R.id.txtcnic);
        mobileno = findViewById(R.id.txtmobileno);
        password = findViewById(R.id.txtpassword);
        city = findViewById(R.id.txtcity);

        database = FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("VoterData");
    }



    public void SaveRecord(View view)
    {
            HashMap<String, Object> voterdata = new HashMap<>();
            voterdata.put("Name", name.getText().toString());
            voterdata.put("Password", password.getText().toString());
            voterdata.put("Email", email.getText().toString());
            voterdata.put("CNIC", cnic.getText().toString());
            String str_cnic = cnic.getText().toString();

            String regexPattern = "\\d{5}-\\d{7}-\\d";
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("VoterData");
            // Perform a query to check if the user already exists
            Query query = usersRef.orderByChild("CNIC").equalTo(str_cnic);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                        boolean userExists = dataSnapshot.exists();
                        if (!userExists) {
                            String uniqueKey = usersRef.push().getKey();
                            usersRef.child(uniqueKey).setValue(str_cnic);
                            // Data doesn't exist, add the new user
                        } else {
                            // Data already exists, handle accordingly
                            cnic.setText("");
                            String id = databaseReference.push().getKey();
                            databaseReference.child(id).removeValue();
                            voterdata.put("CNIC", cnic.getText().toString());
                        }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle any errors that occur
                }
            });
            //Toast.makeText(this, "CNIC already exits", Toast.LENGTH_SHORT).show();

            voterdata.put("Mobile No", mobileno.getText().toString());
            voterdata.put("City", city.getText().toString());
            voterdata.put("vote","");
          //  if (str_cnic.matches(regexPattern)) {
                // The input matches the desired format
                databaseReference.child("voter").child(cnic.getText().toString()).setValue(voterdata);
                // Perform your desired actions here
               // Toast.makeText(this, "Input matches the format", Toast.LENGTH_SHORT).show();
          //  } else {
                // The input does not match the desired format
            //    Toast.makeText(this, "Input does not match the format", Toast.LENGTH_SHORT).show();
          //  }


        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, UserLoginScreen.class);
        startActivity(intent);

    }
}