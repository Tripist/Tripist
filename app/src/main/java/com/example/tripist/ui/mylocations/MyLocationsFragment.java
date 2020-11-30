package com.example.tripist.ui.mylocations;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.Places;
import com.example.tripist.R;
import com.example.tripist.RecyclerViewAdapter;
import com.example.tripist.maps.My_Locations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class MyLocationsFragment extends Fragment  {
    SQLiteDatabase database;
    RecyclerView myRecyclerView;
    ArrayList<Places> lstPlaces;
    ImageButton map_Buttonn;
    FloatingActionButton mymap_fab;
    private MyLocationsViewModel myLocationsViewModel;
    Adapter recyclerViewAdapter;
            //todo refresh table
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstPlaces = new ArrayList<>();
        try {
            database = getActivity().openOrCreateDatabase("Places", MODE_PRIVATE, null);
            Cursor cursor = database.rawQuery("SELECT * FROM my_locations", null);

            int nameIX = cursor.getColumnIndex("name");
            int latitudeIX = cursor.getColumnIndex("latitude");
            int longitudeIX = cursor.getColumnIndex("longitude");

            while (cursor.moveToNext()) {
                String nameFromDatabase = cursor.getString(nameIX);
                String latitudeFromDatabase = cursor.getString(latitudeIX);
                String longitudeFromDatabase = cursor.getString(longitudeIX);

                Double latitude = Double.parseDouble(latitudeFromDatabase);
                Double longitude = Double.parseDouble(longitudeFromDatabase);

                Places place = new Places(nameFromDatabase, latitude, longitude);


                lstPlaces.add(place);

            }

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();


        }

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myLocationsViewModel = new ViewModelProvider(this).get(MyLocationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mylocations, container, false);

        myRecyclerView = root.findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstPlaces);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
        mymap_fab =root.findViewById(R.id.mymap_fab);
        mymap_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMy_Locations();
            }
        });

      // Berkeden anılar
       /* map_Buttonn = root.findViewById(R.id.map_Buttonn);
        map_Buttonn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMy_Locations();
            }
        });
   */
        return root;

    }



    public void showMy_Locations() {
        Intent intent = new Intent(getActivity(), My_Locations.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }


}