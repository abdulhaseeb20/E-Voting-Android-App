package com.example.semesterproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class CastVote extends AppCompatActivity {

    ListView listView;
    String[] Name = {"PTI", "PML N", "MQM", "JUI", "PPP"};
    String[] Area = {"NA-49", "NA-49", "NA-49", "NA-49", "NA-49"};
    int Symbol[] = {R.drawable.symbol1, R.drawable.pmln, R.drawable.mqm, R.drawable.jui,R.drawable.ppp,};
    int i = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_vote);
       listView = findViewById(R.id.lstmulticolumn);
        ListViewAdapter adapterMultiColumn = new ListViewAdapter(this, Symbol, Name, Area);
        listView.setAdapter(adapterMultiColumn);
    }

    public void savePartyName(View view) {
        String voter_cnic = DataHolder.getInstance().getTextValue();
        DatabaseReference reference= FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/").getReference("VoterData");
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("vote",Name[0]);
        reference.child("voter").child(voter_cnic).updateChildren(hashMap);
        Toast.makeText(this, voter_cnic, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, OTPVerfication.class);
        startActivity(intent);

    }

}