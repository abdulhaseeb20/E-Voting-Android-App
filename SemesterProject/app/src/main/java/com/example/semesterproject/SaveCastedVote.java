package com.example.semesterproject;

import androidx.annotation.NonNull;
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
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class SaveCastedVote extends AppCompatActivity {

    TextView Cnic, Party;
    ListView listView;
    String[] Name = {"PTI", "PML N", "MQM", "JUI", "PPP"};
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_casted_vote);
        setContentView(R.layout.castedvote_row);
        Cnic = findViewById(R.id.txtfieldname);
        Party = findViewById(R.id.txtfieldcount);
        sendNotification();
    }

    public void getData(View view) {
        String voter_cnic = DataHolder.getInstance().getTextValue();
        String voter_party = DataHolder.getInstance().getTextValue();
        Cnic.setText(voter_cnic);
        Party.setText(Name[0]);

        DatabaseReference reference= FirebaseDatabase.getInstance("https://semesterproject-a7e26-default-rtdb.firebaseio.com/").getReference("VoterData");
        reference.child("voter").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void sendNotification() {
        String channelId = "100";
        String channelName =  "E-Vote";
        String channelDescription = "Channel Description";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(channelDescription);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.active)
                .setContentTitle("New Vote Added")
                .setContentText("Your vote has been successfully added.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        Random random = new Random();
        int notificationId = random.nextInt(10000);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(notificationId, builder.build());
    }

    public void returnMain(View view) {
        Intent intent=new Intent(this,MainScreen.class);
        startActivity(intent);
    }
}