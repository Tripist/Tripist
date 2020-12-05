package com.example.tripist.controller.navigation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.adapters.RecyclerViewAdapter;
import com.example.tripist.controller.maps.My_Locations;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MyLocationsFragment extends Fragment  {
    SQLiteDatabase database;
    RecyclerView myRecyclerView;
    ArrayList<Places> lstPlaces;
    ImageButton map_Buttonn;
    FloatingActionButton mymap_fab;
    Adapter recyclerViewAdapter;



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

        //  NOT: Viewmodel yapısını silme : myLocationsViewModel = new ViewModelProvider(this).get(MyLocationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mylocations, container, false);

        myRecyclerView = root.findViewById(R.id.recyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstPlaces,database);
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

    @Override
    public void onStart() {
        getData();
        super.onStart();

    }






    public void getData() {
        lstPlaces.clear();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstPlaces,database);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);

        try {

            database = getActivity().openOrCreateDatabase("Places",MODE_PRIVATE,null);
            Cursor cursor = database.rawQuery("SELECT * FROM my_locations",null);

            int nameIx = cursor.getColumnIndex("name");
            int latitudeIx = cursor.getColumnIndex("latitude");
            int longitudeIx = cursor.getColumnIndex("longitude");

            while (cursor.moveToNext()) {

                String nameFromDatabase = cursor.getString(nameIx);
                String latitudeFromDatabase = cursor.getString(latitudeIx);
                String longitudeFromDatabase = cursor.getString(longitudeIx);

                Double latitude = Double.parseDouble(latitudeFromDatabase);
                Double longitude = Double.parseDouble(longitudeFromDatabase);

                Places place = new Places(nameFromDatabase,latitude,longitude);


                lstPlaces.add(place);

            }
            recyclerViewAdapter.notifyDataSetChanged();
            cursor.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        myRecyclerView.setAdapter(recyclerViewAdapter );



    }

}