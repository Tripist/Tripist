package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripist.adapters.ParksAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.controller.maps.Parks;

import java.util.ArrayList;

import static com.example.tripist.database.LocalizationHelper.loadLocale;

public class ParksCategory extends AppCompatActivity {
    //Definition category variables
    private Toolbar toolbar;
    private RecyclerView park_rv;
    private ArrayList<Places> placesArrayList;
    DatabaseHelper databaseHelper;

    @Override   //First Creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_parks_category);

        //Toolbar
        toolbar =findViewById(R.id.park_toolbar);
        toolbar.setTitle(R.string.title_parks);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_28);
        toolbar.setTitleTextAppearance(this, R.style.NunitoBoldFont);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //RecyclerView
        park_rv= findViewById(R.id.park_rv);
        park_rv.setHasFixedSize(true);
        park_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        getData();
    }

    //open the map
    public void showParksMap(View view){
        Intent intent = new Intent(this, Parks.class);
        intent.putExtra("info","new");
        startActivity(intent);
    }

    //call the function to get data from the database
    public void getData(){
        String parks = "parks_gardens";
        placesArrayList = new KategorieDao().KategorieList(databaseHelper,parks);
        ParksAdapter adapter = new ParksAdapter(placesArrayList,getApplicationContext());;
        park_rv.setAdapter(adapter);
    }
}