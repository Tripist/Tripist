package com.example.tripist.navigation;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripist.R;
import com.example.tripist.adapters.My_LocationsAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.maps.MyLocations_Map;
import com.example.tripist.models.Categories;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyLocationsFragment extends Fragment {
    //Definition variables
    SQLiteDatabase database;
    RecyclerView myRecyclerView;
    ArrayList<Categories> lstPlaces;
    FloatingActionButton mymap_fab;
    DatabaseHelper databaseHelper;

    @Override   //First Creation
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(getContext());
        lstPlaces = new KategorieDao().MylocationsList(databaseHelper);
        My_LocationsAdapter myLocationsAdapter = new My_LocationsAdapter(getContext(), lstPlaces, database);

    }

    //ui views of components
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mylocations, container, false);

        myRecyclerView = root.findViewById(R.id.recyclerView);
        My_LocationsAdapter myLocationsAdapter = new My_LocationsAdapter(getContext(), lstPlaces, database);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(myLocationsAdapter);
        myLocationsAdapter.notifyDataSetChanged();
        mymap_fab = root.findViewById(R.id.mymap_fab);
        mymap_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMy_Locations();
            }
        });

        return root;

    }

    //open the map
    public void showMy_Locations() {
        Intent intent = new Intent(getActivity(), MyLocations_Map.class);
        intent.putExtra("info", "new");
        startActivity(intent);

    }

    @Override   //Initialization
    public void onStart() {
        getData();
        super.onStart();

    }

    //call the function to get data from the database
    public void getData() {
        lstPlaces.clear();
        lstPlaces = new KategorieDao().MylocationsList(databaseHelper);
        My_LocationsAdapter myLocationsAdapter = new My_LocationsAdapter(getContext(), lstPlaces, database);
        myLocationsAdapter.notifyDataSetChanged();
        myRecyclerView.setAdapter(myLocationsAdapter);
    }

}