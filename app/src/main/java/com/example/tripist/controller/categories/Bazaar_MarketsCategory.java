package com.example.tripist.controller.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tripist.adapters.BazaarMarketsAdapter;
import com.example.tripist.adapters.CategoryAdapter;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.database.KategorieDao;
import com.example.tripist.models.Places;
import com.example.tripist.R;
import com.example.tripist.controller.maps.Bazaar_Markets;

import java.util.ArrayList;

import static com.example.tripist.database.LocalizationHelper.loadLocale;

public class Bazaar_MarketsCategory extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatActivity activityForBar;
    private RecyclerView bazaar_rv;
    private ArrayList<Places> placesArrayList;
    private BazaarMarketsAdapter adapter;
   DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale(this);
        databaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_bazaar__markets_category);

        //TOOLBAR
        toolbar =findViewById(R.id.bazaar_toolbar);
        toolbar.setTitle(R.string.title_markets);
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
        bazaar_rv= findViewById(R.id.bazaar_rv);
        bazaar_rv.setHasFixedSize(true);
        bazaar_rv.setLayoutManager( new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        getData();
    }
    public void showBazaar_Markets_Map(View view){
        Intent intent = new Intent(this, Bazaar_Markets.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
    public void getData(){
        String bazaar_markets = "bazaar_markets";
        placesArrayList = new KategorieDao().KategorieList(databaseHelper,bazaar_markets);
        BazaarMarketsAdapter adapter = new BazaarMarketsAdapter(placesArrayList,getApplicationContext());;
        bazaar_rv.setAdapter(adapter);
    }
}