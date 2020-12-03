package com.example.tripist.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripist.R;
import com.example.tripist.database.Database_Connection;
import com.example.tripist.views.home.HomeFragment;
import com.example.tripist.views.maps.Bazaar_Markets;
import com.example.tripist.views.maps.Historical_Places;
import com.example.tripist.views.maps.Island_Beachs;
import com.example.tripist.views.maps.Museums;
import com.example.tripist.views.maps.My_Locations;
import com.example.tripist.views.maps.Parks;
import com.example.tripist.views.maps.Religions;
import com.example.tripist.views.maps.Squares;
import com.example.tripist.views.onboarding.OnBoarding;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIMER = 5000;
    public static final String DATABASE_NAME = "Places";
    SQLiteDatabase database;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;
    SharedPreferences onBoardingScreen;
    HomeFragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView2);
        logo = findViewById(R.id.textView);
        slogan = findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        logo.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                if(isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), BottomNav.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, SPLASH_TIMER);



        //create table
        database = SplashScreen.this.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

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















  /*  private void createEmployeeTable() {
        database.execSQL(
                "CREATE TABLE IF NOT EXISTS my_locationss (\n" +
                        "    name varchar(30) NOT NULL,\n" +
                        "    latitude varchar(20) NOT NULL,\n" +
                        "    longitude varchar(20) NOT NULL\n" +
                        ");"
        );
    } */


















}