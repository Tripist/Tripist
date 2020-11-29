package com.example.tripist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Places> {
        ArrayList<Places> placeList ;
        Context context ;

    public Adapter(@NonNull Context context, ArrayList<Places> placesList) {
        super(context, R.layout.listview_my_locations);
        this.placeList = placesList ;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View customView = layoutInflater.inflate(R.layout.listview_my_locations,parent,false);
        TextView nameTextView = customView.findViewById(R.id.nameTextView);
        nameTextView.setText(placeList.get(position).name);
        return customView;
    }
}
