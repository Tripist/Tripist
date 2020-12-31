package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.tripist.adapters.HistoricalPlacesAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.controller.maps.Historical_Places;


import java.util.ArrayList;

import static com.example.tripist.database.LocalizationHelper.loadLocale;

public class HistoricalPlacesCategory extends AppCompatActivity {
    //Definition category variables
    private Toolbar toolbar;
    private RecyclerView historical_rv;
    private ArrayList <Places> placesArrayList;
    DatabaseHelper databaseHelper;
    ImageButton google;

    @Override   //First Creation
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_historical_places_category);
        loadLocale(this);

        ////Toolbar
        toolbar =findViewById(R.id.historical_toolbar);
        toolbar.setTitle(R.string.title_historical);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_28);
        toolbar.setTitleTextAppearance(this, R.style.NunitoBoldFont);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        google = findViewById(R.id.googlef);
        //RecyclerView
        historical_rv= findViewById(R.id.historical_rv);
        historical_rv.setHasFixedSize(true);
        historical_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        getData();

    }

    //open the map
     public void showHistorical_Places_Map (View view){
        Intent intent = new Intent(this, Historical_Places.class);
        intent.putExtra("info", "new");
        startActivity(intent);
      }

      //call the function to get data from the database
      public void getData(){
        placesArrayList = new KategorieDao().Historical_Places(databaseHelper);
        HistoricalPlacesAdapter adapter = new HistoricalPlacesAdapter(placesArrayList,getApplicationContext());;
        historical_rv.setAdapter(adapter);
    }
}