package com.example.tripist.views.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripist.adapters.CategoryAdapter;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.views.maps.Island_Beachs;

import java.util.ArrayList;

public class Island_BeachesCategory extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatActivity activityForBar;
    private RecyclerView island_rv;
    private ArrayList<Places> placesArrayList;
    private CategoryAdapter adapter;
    private Database_Connection dbconnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_island__beaches_category);

        //TOOLBAR
        toolbar =findViewById(R.id.island_toolbar);
        toolbar.setTitle("Adalar ve Sahiller");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_28);
        // TODO Sağ taraftaki icon hatası

        // TODO Database işlemleri

        //RECYCLERVIEW
        island_rv= findViewById(R.id.island_rv);
        island_rv.setHasFixedSize(true);
        island_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new CategoryAdapter(placesArrayList, this);
        //   island_rv.setAdapter(adapter);
    }
    public void showIsland_Beachs_Map(View view){

        Intent intent = new Intent(this, Island_Beachs.class);
        intent.putExtra("info","new");
        startActivity(intent);
    }
}