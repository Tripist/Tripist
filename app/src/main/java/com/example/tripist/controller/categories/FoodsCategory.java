package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.tripist.adapters.CategoryAdapter;
import com.example.tripist.adapters.FoodsAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Foods;
import com.example.tripist.models.Places;
import com.example.tripist.R;

import java.util.ArrayList;

public class FoodsCategory extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatActivity activityForBar;
    private RecyclerView food_rv;
    private ArrayList<Foods> placesArrayList;
    private CategoryAdapter adapter;
    private Database_Connection dbconnection;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_foods_category);

        //TOOLBAR
        toolbar =findViewById(R.id.food_toolbar);
        toolbar.setTitle(R.string.title_foods);
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
        food_rv= findViewById(R.id.food_rv);
        food_rv.setHasFixedSize(true);
        food_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        getData();
        //   food_rv.setAdapter(adapter);
    }
    public void getData(){
        String foods = "foods";
        placesArrayList = new KategorieDao().FoodsList(databaseHelper);
        FoodsAdapter adapter = new FoodsAdapter(placesArrayList,getApplicationContext());;
        food_rv.setAdapter(adapter);
    }

}