package com.example.tripist.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tripist.R;
import com.example.tripist.database.DatabaseHelper;
import com.example.tripist.database.KategorieDao;

import static com.example.tripist.database.LocalizationHelper.loadLocale;

public class SplashScreen extends AppCompatActivity {
    //Time to open the homepage
    private static int SPLASH_TIMER = 5000;
    //Variables defined
    SQLiteDatabase database;
    Animation topAnim, bottomAnim;
    ImageView image;
    TextView logo, slogan;
    SharedPreferences onBoardingScreen;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
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

        //Set Animation
        image.setAnimation(topAnim);
        logo.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);

        //Onboarding pages only appear on first launch
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

       loadLocale(this);
       // TABLE DATA
        new KategorieDao().add_religions(databaseHelper);
        new KategorieDao().add_bazaarmarkets(databaseHelper);
        new KategorieDao().add_historicalplaces(databaseHelper);
        new KategorieDao().add_islandsandbeachs(databaseHelper);
        new KategorieDao().add_museums(databaseHelper);
        new KategorieDao().add_parks(databaseHelper);
        new KategorieDao().add_squares(databaseHelper);
        new KategorieDao().add_food(databaseHelper);


    }



}