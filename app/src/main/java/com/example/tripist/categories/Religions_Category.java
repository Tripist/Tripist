package com.example.tripist.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripist.adapters.ReligionsAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.maps.Religions_Map;
import com.example.tripist.models.Categories;
import com.example.tripist.R;

import java.util.ArrayList;

import static com.example.tripist.controller.LocalizationController.loadLocale;

public class Religions_Category extends AppCompatActivity {
    //Definition category variables
    private Toolbar toolbar;
    private RecyclerView religion_rv;
    private ArrayList<Categories> categoriesArrayList;
    DatabaseHelper databaseHelper;

    @Override   //First Creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_religions_category);

        //Toolbar
        toolbar =findViewById(R.id.religion_toolbar);
        toolbar.setTitle(R.string.title_religions);
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
        religion_rv= findViewById(R.id.religion_rv);
        religion_rv.setHasFixedSize(true);
        religion_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
       getData();
    }

    //open the map
    public void showReligionsMap(View view){
        Intent intent = new Intent(this, Religions_Map.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }

    //call the function to get data from the database
    public void getData(){
        String religions = "religions";
        categoriesArrayList = new KategorieDao().KategorieList(databaseHelper,religions);
        ReligionsAdapter adapter = new ReligionsAdapter(categoriesArrayList,getApplicationContext());;
        religion_rv.setAdapter(adapter);
    }
}