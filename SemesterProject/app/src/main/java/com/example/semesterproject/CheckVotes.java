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

import org.w3c.dom.Text;

public class CheckVotes extends AppCompatActivity {

    TextView textView1, textView2, textView3, textView4, textView5;
    int count_pti=0, count_pmln=0, count_mqm=0, count_jui=0, count_ppp = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_votes);
        textView1=findViewById(R.id.txtfieldcountpti);
        textView2=findViewById(R.id.txtfieldcountpmln);
        textView3=findViewById(R.id.txtfieldcountmqm);
        textView4=findViewById(R.id.txtfieldcountjui);
        textView5=findViewById(R.id.txtfieldcountppp);

        DatabaseReference reference= FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/").getReference("VoterData");
        reference.child("voter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    if((dataSnapshot.child("vote").getValue().toString()).equals("PTI")){
                        count_pti++;
                    }
                    else if ((dataSnapshot.child("vote").getValue().toString()).equals("PMLN"))
                    {
                        count_pmln++;
                    }
                    else if ((dataSnapshot.child("vote").getValue().toString()).equals("MQM"))
                    {
                        count_mqm++;
                    }
                    else if ((dataSnapshot.child("vote").getValue().toString()).equals("JUI"))
                    {
                        count_jui++;
                    }
                    else if ((dataSnapshot.child("vote").getValue().toString()).equals("PPP"))
                    {
                        count_ppp++;
                    }
                }
                textView1.setText(String.valueOf(count_pti));
                textView2.setText(String.valueOf(count_pmln));
                textView3.setText(String.valueOf(count_mqm));
                textView4.setText(String.valueOf(count_jui));
                textView5.setText(String.valueOf(count_ppp));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //PENDING

}