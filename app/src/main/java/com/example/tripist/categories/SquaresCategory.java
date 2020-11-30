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

public class SquaresCategory extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatActivity activityForBar;
    private RecyclerView square_rv;
    private ArrayList<Places> placesArrayList;
    private CategoryAdapter adapter;
    private Database_Connection dbconnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squares_category);

        //TOOLBAR
        toolbar =findViewById(R.id.square_toolbar);
        toolbar.setTitle("Tarihi Yerler");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_28);
        // TODO Sağ taraftaki icon hatası

        // TODO Database işlemleri

        //RECYCLERVIEW
        square_rv= findViewById(R.id.square_rv);
        square_rv.setHasFixedSize(true);
        square_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new CategoryAdapter(placesArrayList, this);
        //   square_rv.setAdapter(adapter);
    }
}