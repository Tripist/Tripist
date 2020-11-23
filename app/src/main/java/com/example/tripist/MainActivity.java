package com.example.tripist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripist.maps.Bazaar_Markets;
import com.example.tripist.maps.Historical_Places;
import com.example.tripist.maps.Island_Beachs;
import com.example.tripist.maps.Museums;

import com.example.tripist.maps.My_Locations;
import com.example.tripist.maps.Parks;
import com.example.tripist.maps.Religions;
import com.example.tripist.maps.Squares;
import com.example.tripist.ui.home.HomeFragment;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIMER = 5000;
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

    public void showMyLocationsPage(View view){

        Intent intent = new Intent(this,MyLocationsPage.class);

        startActivity(intent);

    }
















}