package com.example.tripist;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class Database_Connection {

    public static void databaseprepare(SQLiteDatabase database) {
        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS bazaar_markets (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS historical_places (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS island_beachs (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS museums (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS my_favourites (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS my_locations (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS parks_gardens (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS religions (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS squares (id INTEGER PRIMARY KEY,name VARCHAR, latitude VARCHAR, longitude VARCHAR)");
            database.execSQL("CREATE TABLE IF NOT EXISTS foods (id INTEGER PRIMARY KEY,name VARCHAR)");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void add_religions(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public static void add_bazaarmarkets(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public static void add_historicalplaces(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public static void add_islandsandbeachs(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public static void add_museums(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public static void add_myfavourites(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }

    public static void add_mylocations(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public static void add_parks(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public static void add_squares(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public static void add_food(SQLiteDatabase database) {

        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
}