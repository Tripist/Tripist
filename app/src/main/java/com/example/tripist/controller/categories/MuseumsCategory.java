package com.example.tripist.controller.categories;

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
import com.example.tripist.controller.maps.Museums;

import java.util.ArrayList;

public class MuseumsCategory extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatActivity activityForBar;
    private RecyclerView museum_rv;
    private ArrayList<Places> placesArrayList;
    private CategoryAdapter adapter;
    private Database_Connection dbconnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museums_category);

        //TOOLBAR
        toolbar =findViewById(R.id.museum_toobar);
        toolbar.setTitle(R.string.title_museum);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_28);
        toolbar.setTitleTextAppearance(this, R.style.NunitoBoldFont);

        // TODO Database işlemleri

        //RECYCLERVIEW
        museum_rv= findViewById(R.id.museum_rv);
        museum_rv.setHasFixedSize(true);
        museum_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new CategoryAdapter(placesArrayList, this);
        //   museum_rv.setAdapter(adapter);
    }
    public void showMuseumsMap(View view){
        Intent intent = new Intent(this, Museums.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
}