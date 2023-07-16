package com.example.semesterproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddParty extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText name, symbol, area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);
         name =  findViewById(R.id.txtpartyname);
         symbol = findViewById(R.id.txtpartysymbol);
         area = findViewById(R.id.txtarea);

        database = FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("PartyData");
        databaseReference.setValue("This is a database for Party Data");
    }

    public void moveToCastVote(View view) {
        Intent intent = new Intent(this, CastVote.class);
        startActivity(intent);
    }

    public void moveToSaveParty(View view) {
        Map<String, Object> partydata = new HashMap<>();
        partydata.put("Name", name.getText().toString());
        partydata.put("Symbol", symbol.getText().toString());
        partydata.put("Area", area.getText().toString());
        databaseReference.child("party").child(name.getText().toString()).setValue(partydata);
        Toast.makeText(this, "Party added saved successfully", Toast.LENGTH_SHORT).show();
        //Move to User Login Screen
        Intent intent = new Intent(this, AdminDashboard.class);
        startActivity(intent);
    }
}