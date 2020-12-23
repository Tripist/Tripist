package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripist.adapters.CategoryAdapter;
import com.example.tripist.adapters.ParksAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.controller.maps.Parks;

import java.util.ArrayList;

public class ParksCategory extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatActivity activityForBar;
    private RecyclerView park_rv;
    private ArrayList<Places> placesArrayList;
    private Database_Connection dbconnection;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_parks_category);

        //TOOLBAR
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
        // TODO Database işlemleri

        //RECYCLERVIEW
        park_rv= findViewById(R.id.park_rv);
        park_rv.setHasFixedSize(true);
        park_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        getData();
        //   park_rv.setAdapter(adapter);
    }
    public void showParksMap(View view){
        Intent intent = new Intent(this, Parks.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
    public void getData(){
        String parks = "parks_gardens";
        placesArrayList = new KategorieDao().KategorieList(databaseHelper,parks);
        ParksAdapter adapter = new ParksAdapter(placesArrayList,getApplicationContext());;
        park_rv.setAdapter(adapter);
    }
}