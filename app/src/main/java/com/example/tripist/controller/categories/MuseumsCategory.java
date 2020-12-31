package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripist.adapters.MuseumsAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.controller.maps.Museums;

import java.util.ArrayList;

import static com.example.tripist.database.LocalizationHelper.loadLocale;

public class MuseumsCategory extends AppCompatActivity {
    //Definition category variables
    private Toolbar toolbar;
    private RecyclerView museum_rv;
    private ArrayList<Places> placesArrayList;
    DatabaseHelper databaseHelper;

    @Override   //First Creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_museums_category);

        //Toolbar
        toolbar =findViewById(R.id.museum_toobar);
        toolbar.setTitle(R.string.title_museum);
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
        museum_rv= findViewById(R.id.museum_rv);
        museum_rv.setHasFixedSize(true);
        museum_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        getData();
    }

    //open the map
    public void showMuseumsMap(View view){
        Intent intent = new Intent(this, Museums.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }

    //call the function to get data from the database
    public void getData(){
        String museums = "museums";
        placesArrayList = new KategorieDao().KategorieList(databaseHelper,museums);
        MuseumsAdapter adapter = new MuseumsAdapter(placesArrayList,getApplicationContext());;
        museum_rv.setAdapter(adapter);
    }
}