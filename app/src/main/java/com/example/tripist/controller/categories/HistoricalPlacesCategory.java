package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.tripist.adapters.CategoryAdapter;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.controller.maps.Historical_Places;


import java.util.ArrayList;

public class HistoricalPlacesCategory extends AppCompatActivity {
   private Toolbar toolbar;
   private AppCompatActivity activityForBar;
   private RecyclerView historical_rv;
   private ArrayList <Places> placesArrayList;
   private CategoryAdapter adapter;
   //private Database_Connection dbconnection;
    ImageButton fav;
    ImageButton google;
   SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        database = this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        setContentView(R.layout.activity_historical_places_category);


        //TOOLBAR
        //getSupportActionBar().hide();
        toolbar =findViewById(R.id.historical_toolbar);
        toolbar.setTitle(R.string.title_historical);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_28);
        toolbar.setTitleTextAppearance(this, R.style.NunitoBoldFont);
        fav = findViewById(R.id.fav);
        google = findViewById(R.id.google);
        // TODO Database işlemleri

        //RECYCLERVIEW
        historical_rv= findViewById(R.id.historical_rv);
        historical_rv.setHasFixedSize(true);
        historical_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));


            getData();


    }
        public void showHistorical_Places_Map (View view){
            Intent intent = new Intent(this, Historical_Places.class);
            intent.putExtra("info", "new");
            startActivity(intent);

        }

    public void getData(){
        placesArrayList = new ArrayList<>();
       // adapter = new CategoryAdapter(placesArrayList, this);
        CategoryAdapter adapter = new CategoryAdapter(placesArrayList,getApplicationContext());
       // historical_rv.setLayoutManager(new LinearLayoutManager(get));
        historical_rv.setAdapter(adapter);


        Cursor cursor = database.rawQuery("SELECT * FROM historical_places", null);

            int nameIX = cursor.getColumnIndex("name");
            int latitudeIX = cursor.getColumnIndex("latitude");
            int longitudeIX = cursor.getColumnIndex("longitude");

            while (cursor.moveToNext()) {
                String nameFromDatabase = cursor.getString(nameIX);
                String latitudeFromDatabase = cursor.getString(latitudeIX);
                String longitudeFromDatabase = cursor.getString(longitudeIX);
                String a = "ayasofya";
                Double latitude = Double.parseDouble(latitudeFromDatabase);
                Double longitude = Double.parseDouble(longitudeFromDatabase);

                Places place = new Places(nameFromDatabase, latitude, longitude,a);


                placesArrayList.add(place);


            }

            adapter.notifyDataSetChanged();
            cursor.close();




        historical_rv.setAdapter(adapter);
    }

   /* public void favImage(){
        toggleButton.setChecked(false);

        fav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.d02n));
        fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.d02d));
                else
                    toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.d02n));
            }
        });
    } */








    

}