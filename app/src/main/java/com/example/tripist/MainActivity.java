package com.example.tripist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tripist.maps.Bazaar_Markets;
import com.example.tripist.maps.Historical_Places;
import com.example.tripist.maps.Island_Beachs;
import com.example.tripist.maps.Museums;
import com.example.tripist.maps.My_Favourites;
import com.example.tripist.maps.My_Locations;
import com.example.tripist.maps.Parks;
import com.example.tripist.maps.Religions;
import com.example.tripist.maps.Squares;

public class MainActivity extends AppCompatActivity {
   Button BazaarMarkets_Button;
   Button HistoricalPlaces_Button;
   Button IslandBeachs_Button;
   Button Museums_Button;
   Button MyLocations_Button;
   Button Parks_Button;
   Button Religions_Button;
   Button Squares_Button;
   Button MyLocationsPage_Button;
   Button MyyFavourites_Button;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //create table
        database = MainActivity.this.openOrCreateDatabase("Places", MODE_PRIVATE, null);
        Database_Connection.databaseprepare(database);

        //add data
        Database_Connection.add_religions(database);
        Database_Connection.add_bazaarmarkets(database);
        Database_Connection.add_historicalplaces(database);
        Database_Connection.add_islandsandbeachs(database);
        Database_Connection.add_museums(database);
        Database_Connection.add_myfavourites(database);
        Database_Connection.add_mylocations(database);
        Database_Connection.add_parks(database);
        Database_Connection.add_squares(database);
        Database_Connection.add_food(database);
    }

    public void showIsland_Beachs(View view){

        Intent intent = new Intent(this, Island_Beachs.class);
        intent.putExtra("info","new");
        startActivity(intent);
    }

    public void showMy_Locations(View view){

        Intent intent = new Intent(this, My_Locations.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }

    public void showMy_Favourites(View view){

        Intent intent = new Intent(this, My_Favourites.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }

    public void showBazaar_Markets(View view){
        Intent intent = new Intent(this, Bazaar_Markets.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }

    public void showHistorical_Places(View view){
        Intent intent = new Intent(this, Historical_Places.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
    public void showMuseums(View view){
        Intent intent = new Intent(this, Museums.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
    public void showParks(View view){
        Intent intent = new Intent(this, Parks.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
    public void showReligions(View view){
        Intent intent = new Intent(this, Religions.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
    public void showSquares(View view){
        Intent intent = new Intent(this, Squares.class);
        intent.putExtra("info","new");
        startActivity(intent);

    }
        /////////////////////77
    public void showMyLocationsPage(View view){

        Intent intent = new Intent(this,MyLocationsPage.class);

        startActivity(intent);

    }








}