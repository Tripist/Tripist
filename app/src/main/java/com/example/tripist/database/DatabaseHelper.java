package com.example.tripist.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.tripist.models.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static android.content.Context.MODE_PRIVATE;

public class    DatabaseHelper extends SQLiteOpenHelper {

    // Constructor
    public DatabaseHelper(Context context) {
        super(context,"Places", null, 1);
    }


    @Override   //Create a table in a database
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE IF NOT EXISTS bazaar_markets (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR,image VARCHAR, isim VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS historical_places (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR,image VARCHAR, isim VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS island_beaches (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR,image VARCHAR, isim VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS museums (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR,image VARCHAR, isim VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS my_favourites (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR,image VARCHAR, isim VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS my_locations (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR,image VARCHAR, isim VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS parks_gardens (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR,image VARCHAR, isim VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS religions (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR,image VARCHAR, isim VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS squares (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR,image VARCHAR, isim VARCHAR)");
        database.execSQL("CREATE TABLE IF NOT EXISTS foods (id INTEGER PRIMARY KEY,name VARCHAR,image VARCHAR,isim VARCHAR)");
    }


    @Override   //Drop table
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bazaar_markets");
        db.execSQL("DROP TABLE IF EXISTS  historical_places");
        db.execSQL("DROP TABLE IF EXISTS  island_beaches");
        db.execSQL("DROP TABLE IF EXISTS  museums");
        db.execSQL("DROP TABLE IF EXISTS  my_favourites");
        db.execSQL("DROP TABLE IF EXISTS  my_locations");
        db.execSQL("DROP TABLE IF EXISTS  parks_gardens");
        db.execSQL("DROP TABLE IF EXISTS  religions");
        db.execSQL("DROP TABLE IF EXISTS  squares");
        db.execSQL("DROP TABLE IF EXISTS  foods");
        onCreate(db);
    }


}
