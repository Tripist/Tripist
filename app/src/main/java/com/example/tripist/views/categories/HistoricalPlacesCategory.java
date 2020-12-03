package com.example.tripist.views.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.tripist.adapters.CategoryAdapter;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.views.maps.Historical_Places;


import java.util.ArrayList;

public class HistoricalPlacesCategory extends AppCompatActivity {
   private Toolbar toolbar;
   private AppCompatActivity activityForBar;
   private RecyclerView historical_rv;
   private ArrayList <Places> placesArrayList;
   private CategoryAdapter adapter;
   private Database_Connection dbconnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_places_category);


        //TOOLBAR
        //getSupportActionBar().hide();
        toolbar =findViewById(R.id.historical_toolbar);
        toolbar.setTitle("Tarihi Yerler");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_28);
        // TODO Sağ taraftaki icon hatası

        // TODO Database işlemleri

        //RECYCLERVIEW
        historical_rv= findViewById(R.id.historical_rv);
        historical_rv.setHasFixedSize(true);
        historical_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new CategoryAdapter(placesArrayList, this);
     //   historical_rv.setAdapter(adapter);



    }
    public void showHistorical_Places_Map(View view){
        Intent intent = new Intent(this, Historical_Places.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
}