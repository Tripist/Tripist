package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.tripist.adapters.CategoryAdapter;
import com.example.tripist.adapters.RecyclerViewAdapter;
import com.example.tripist.controller.navigation.HomeFragment;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.controller.maps.Historical_Places;


import java.util.ArrayList;

import static com.example.tripist.database.LocalizationHelper.loadLocale;

public class HistoricalPlacesCategory extends AppCompatActivity {
   private Toolbar toolbar;
   private AppCompatActivity activityForBar;
   private RecyclerView historical_rv;
   private ArrayList <Places> placesArrayList;
   private CategoryAdapter adapter;
   DatabaseHelper databaseHelper;
    ImageButton fav;
    ImageButton google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_historical_places_category);

        loadLocale(this);
        //TOOLBAR
        //getSupportActionBar().hide();
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
        placesArrayList = new KategorieDao().Historical_Places(databaseHelper);
        CategoryAdapter adapter = new CategoryAdapter(placesArrayList,getApplicationContext());;
        historical_rv.setAdapter(adapter);
    }
}