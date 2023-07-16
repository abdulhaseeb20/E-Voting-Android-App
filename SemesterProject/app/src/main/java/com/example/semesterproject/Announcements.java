package com.example.semesterproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Announcements extends AppCompatActivity {

    TextView textView;
    int count_pti=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        textView=findViewById(R.id.txtshowresults);
        DatabaseReference reference= FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/").getReference("VoterData");
        reference.child("voter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // int count=0;
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    if((dataSnapshot.child("vote").getValue().toString()).equals("PTI")){
                        count_pti++;
                    }
                }
                textView.setText("PTI won with "+String.valueOf(count_pti)+" votes ");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}