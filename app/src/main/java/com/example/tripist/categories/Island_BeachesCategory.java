package com.example.tripist.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.tripist.Database_Connection;
import com.example.tripist.Places;
import com.example.tripist.R;

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
}