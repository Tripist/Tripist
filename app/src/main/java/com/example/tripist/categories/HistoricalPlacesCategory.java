package com.example.tripist.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.tripist.Database_Connection;
import com.example.tripist.Places;
import com.example.tripist.R;


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
}