package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripist.adapters.CategoryAdapter;
import com.example.tripist.adapters.IslandBeachsAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.controller.maps.Island_Beachs;

import java.util.ArrayList;

import static com.example.tripist.database.LocalizationHelper.loadLocale;

public class Island_BeachesCategory extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatActivity activityForBar;
    private RecyclerView island_rv;
    private ArrayList<Places> placesArrayList;
    private IslandBeachsAdapter adapter;
    private Database_Connection dbconnection;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_island__beaches_category);

        //TOOLBAR
        toolbar =findViewById(R.id.island_toolbar);
        toolbar.setTitle(R.string.title_island);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_28);
        toolbar.setTitleTextAppearance(this, R.style.NunitoBoldFont);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // TODO Database işlemleri

        //RECYCLERVIEW
        island_rv= findViewById(R.id.island_rv);
        island_rv.setHasFixedSize(true);
        island_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        getData();
        //   island_rv.setAdapter(adapter);
    }
    public void showIsland_Beachs_Map(View view){

        Intent intent = new Intent(this, Island_Beachs.class);
        intent.putExtra("info","new");
        startActivity(intent);
    }
    public void getData(){
        String island_beaches = "island_beaches";
        placesArrayList = new KategorieDao().KategorieList(databaseHelper,island_beaches);
        IslandBeachsAdapter adapter = new IslandBeachsAdapter(placesArrayList,getApplicationContext());;
        island_rv.setAdapter(adapter);
    }
}