package com.example.tripist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button;
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

    public void nextPage(View view){

        Intent intent = new Intent(this,Island_Beachs.class);

        startActivity(intent);
    }


    }


