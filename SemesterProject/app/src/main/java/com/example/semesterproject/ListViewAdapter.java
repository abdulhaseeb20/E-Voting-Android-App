package com.example.semesterproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListViewAdapter extends ArrayAdapter<String> {
    Activity activity;
    String[] Name;
    String[] Area;
    int[] Symbol;
    String Cnic, Party;

    public ListViewAdapter(@NonNull Activity activity, int[] image, String[] name, String[] area) {
        super(activity, R.layout.vote_row, area);
        this.activity = activity;
        Symbol = image;
        Name = name;
        Area = area;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View viewHolder = inflater.inflate(R.layout.vote_row, null);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView name = viewHolder.findViewById(R.id.txtfieldname);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView text = viewHolder.findViewById(R.id.txtfieldarea);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView imageView = viewHolder.findViewById(R.id.imglistview);
        imageView.setImageResource(Symbol[position]);
        name.setText(Name[position]);
        text.setText(Area[position]);
        return viewHolder;
    }

}
