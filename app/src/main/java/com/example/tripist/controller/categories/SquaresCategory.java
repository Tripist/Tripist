package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripist.adapters.SquaresAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.controller.maps.Squares;

import java.util.ArrayList;

import static com.example.tripist.database.LocalizationHelper.loadLocale;

public class SquaresCategory extends AppCompatActivity {
    //Definition category variables
    private Toolbar toolbar;
    private RecyclerView square_rv;
    private ArrayList<Places> placesArrayList;
    DatabaseHelper databaseHelper;

    @Override   //First Creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_squares_category);

        //Toolbar
        toolbar =findViewById(R.id.square_toolbar);
        toolbar.setTitle(R.string.title_squares);
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
        square_rv= findViewById(R.id.square_rv);
        square_rv.setHasFixedSize(true);
        square_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        getData();
    }

    //open the map
    public void showSquaresMap(View view){
        Intent intent = new Intent(this, Squares.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }

    //call the function to get data from the database
    public void getData(){
        String squares = "squares";
        placesArrayList = new KategorieDao().KategorieList(databaseHelper,squares);
        SquaresAdapter adapter = new SquaresAdapter(placesArrayList,getApplicationContext());;
       square_rv.setAdapter(adapter);
    }
}